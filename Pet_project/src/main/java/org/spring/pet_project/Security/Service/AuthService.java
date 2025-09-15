package org.spring.pet_project.Security.Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.spring.pet_project.Controller.DTO.Request.LogInRequestDto;
import org.spring.pet_project.Controller.DTO.Request.SignUpRequestDto;
import org.spring.pet_project.Controller.DTO.Response.AuthDataResponse;
import org.spring.pet_project.Mapper.RequestMapper;
import org.spring.pet_project.Mapper.ResponseMapper;
import org.spring.pet_project.Model.AppUserToken;
import org.spring.pet_project.Repository.AppUserRepository;
import org.spring.pet_project.Repository.AppUserTokenRepository;
import org.spring.pet_project.Security.JwtCore;
import org.spring.pet_project.Service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserRepository appUserRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtCore jwtCore;

    private final RequestMapper requestMapper;

    private final AppUserService appUserService;

    private final AppUserTokenRepository appUserTokenRepository;

    @Transactional
    public AuthDataResponse logIn(@Valid LogInRequestDto logInRequestDto) {
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInRequestDto.email(), logInRequestDto.password()));
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized operation");
        }

        var appUser = appUserRepository.findAppUserByEmail(logInRequestDto.email()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        var authDataResponse = jwtCore.generateTokens(authentication);

        appUserTokenRepository.save(new AppUserToken(appUser.getId(), authDataResponse.jwtTokens().refreshToken(), authDataResponse.jwtTokens().refreshExpirationTime()));
        return authDataResponse;
    }

    @Transactional
    public AuthDataResponse signUp(@Valid SignUpRequestDto signUpRequestDto) {
        appUserRepository.findAppUserByEmail(signUpRequestDto.email()).ifPresent(user -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this email already exists.");
        });

        if (!signUpRequestDto.password().equals(signUpRequestDto.confirmPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords do not match.");

        var appUser = requestMapper.toAppUser(signUpRequestDto);
        appUser.setPassword(passwordEncoder.encode(signUpRequestDto.password()));
        appUserRepository.save(appUser);

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signUpRequestDto.email(), signUpRequestDto.password()));
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized operation");
        }

        var authDataResponse = jwtCore.generateTokens(authentication);
        appUserTokenRepository.save(new AppUserToken(appUser.getId(), authDataResponse.jwtTokens().refreshToken(), authDataResponse.jwtTokens().refreshExpirationTime()));
        return authDataResponse;
    }

    @Transactional
    public AuthDataResponse refresh(String refreshToken) {
        if (refreshToken == null || !refreshToken.startsWith("Bearer "))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid refresh token");

        String token = refreshToken.substring(7);
        var appUserToken = appUserTokenRepository.findByToken(token).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized operation"));
        var appUser = appUserService.getEntityById(appUserToken.getId());
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getEmail(), appUser.getPassword()));
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized operation");
        }

        var authDataResponse = jwtCore.generateTokens(authentication);

        appUserTokenRepository.save(new AppUserToken(appUser.getId(), authDataResponse.jwtTokens().refreshToken(), authDataResponse.jwtTokens().refreshExpirationTime()));
        return authDataResponse;
    }

    @Transactional
    public void logout(String accessToken) {
        if (accessToken != null && accessToken.startsWith("Bearer "))
            appUserTokenRepository.deleteByToken(accessToken.substring(7));
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid refresh token");
    }
}
