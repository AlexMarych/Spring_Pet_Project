package org.spring.pet_project.Controller.DTO.Request.Update;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

public record UpdateBoardDto(
        @NotNull
        UUID id,
        @NotEmpty
        @Min(2)
        @Max(30)
        String title,
        @NotEmpty
        @Max(100)
        String description,
        Set<UUID> memberIds
) {
}
