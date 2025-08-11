package org.spring.pet_project.Controller.DTO.Response;

import java.util.UUID;

public record AuthDataResponse(
        JwtTokens jwtTokens,
        UUID id,
        String username,
        String email
) {
}
