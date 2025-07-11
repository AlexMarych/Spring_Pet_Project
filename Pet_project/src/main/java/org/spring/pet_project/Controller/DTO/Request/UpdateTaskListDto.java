package org.spring.pet_project.Controller.DTO.Request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateTaskListDto(
        @NotNull
        UUID id,

        @NotEmpty
        @Min(2)
        @Max(20)
        String title
) {
}
