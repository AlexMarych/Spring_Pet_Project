package org.spring.pet_project.Controller.DTO.Request;

import jakarta.validation.constraints.*;

import java.util.Set;
import java.util.UUID;

public record CreateBoardDto(
        @NotBlank
        @Min(2)
        @Max(30)
        String title,
        @NotEmpty
        @Max(100)
        String description,
        @NotNull
        Set<UUID> memberIds
) {
}
