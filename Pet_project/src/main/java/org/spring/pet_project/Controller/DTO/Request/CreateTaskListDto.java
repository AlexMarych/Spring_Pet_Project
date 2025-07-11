package org.spring.pet_project.Controller.DTO.Request;

import jakarta.validation.constraints.*;

import java.util.UUID;

public record CreateTaskListDto(
        @NotBlank
        @Min(2)
        @Max(20)
        String title,
        @NotNull
        UUID board
) {
}
