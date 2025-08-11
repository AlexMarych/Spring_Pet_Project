package org.spring.pet_project.Controller.DTO.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record RequestAppUserDto(
        @NotBlank
        @Min(2)
        @Max(30)
        String name,

        @NotBlank
        @Email
        @Max(30)
        @Min(8)
        String email
) {
}
