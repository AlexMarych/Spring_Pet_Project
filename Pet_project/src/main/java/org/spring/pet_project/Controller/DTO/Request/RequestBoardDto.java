package org.spring.pet_project.Controller.DTO.Request;

import jakarta.validation.constraints.*;

public record RequestBoardDto(
        @NotBlank
        @Min(2)
        @Max(30)
        String title,
        @NotEmpty
        @Max(100)
        String description
) {
}
