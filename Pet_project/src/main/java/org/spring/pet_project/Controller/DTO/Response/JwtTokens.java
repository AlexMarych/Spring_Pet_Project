package org.spring.pet_project.Controller.DTO.Response;

import java.time.Instant;

public record JwtTokens(
        String accessToken,
        String refreshToken,
        Instant accessExpirationTime,
        Instant refreshExpirationTime
) {
}
