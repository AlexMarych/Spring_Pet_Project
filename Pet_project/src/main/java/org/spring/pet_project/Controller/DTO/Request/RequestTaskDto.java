package org.spring.pet_project.Controller.DTO.Request;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record RequestTaskDto(
        @NotBlank
        @Min(2)
        @Max(20)
        String name,
        @NotEmpty
        @Max(500)
        String description,
        LocalDateTime dueDate
) {
}
