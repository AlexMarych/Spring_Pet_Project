package org.spring.pet_project.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.spring.pet_project.Controller.DTO.Response.AuthDataResponse;
import org.spring.pet_project.Controller.DTO.Response.JwtTokens;
import org.spring.pet_project.Security.Details.AppUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtCore {
    @Value("${app.access-secret}")
    private String accessSecret;

    @Value("${app.refresh-secret}")
    private String refreshSecret;

    @Value("${app.access-expiration}")
    private String accessLifetime;

    @Value("${app.refresh-expiration}")
    private String refreshLifetime;

    public AuthDataResponse generateTokens(Authentication authentication) {
        var appUserDetails = (AppUserDetails) authentication.getPrincipal();

        var accessExpirationTime = Instant.now().plusSeconds(Long.parseLong(accessLifetime));
        var refreshExpirationTime = Instant.now().plusSeconds(Long.parseLong(refreshLifetime));

        return new AuthDataResponse(
                new JwtTokens(
                        this.generateToken(accessSecret, appUserDetails, accessExpirationTime),
                        this.generateToken(refreshSecret, appUserDetails, refreshExpirationTime),
                        accessExpirationTime,
                        refreshExpirationTime
                ),
                appUserDetails.getId(),
                appUserDetails.getRealUsername(),
                appUserDetails.getEmail()
        );
    }

    public String getUsernameFromJwt(String token) {
        return Jwts.parser()
                .verifyWith(this.generateKey(accessSecret))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    private String generateToken(String secret, AppUserDetails appUserDetails, Instant expirationTime){
        return Jwts.builder()
                .subject(appUserDetails.getEmail())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(expirationTime))
                .claim("id", appUserDetails.getId())
                .claim("username", appUserDetails.getUsername())
                .claim("email", appUserDetails.getEmail())
                .signWith(this.generateKey(secret))
                .compact();
    }

    private SecretKey generateKey(String secret) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
}
