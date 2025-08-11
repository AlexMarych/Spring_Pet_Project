package org.spring.pet_project.Controller.DTO.Request;

import jakarta.validation.constraints.NotNull;

public record LogInRequestDto(
        @NotNull
        String email,

        @NotNull
        String password
) {
}
