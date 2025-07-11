package org.spring.pet_project.Controller.DTO.Request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record UpdateTaskDto(
        @NotNull
        UUID Id,
        @Min(2)
        @Max(20)
        @NotEmpty
        String name,
        @NotEmpty
        @Max(500)
        String description,
        LocalDateTime dueDate,
        UUID listOfTasks,
        Set<UUID> members
) {
}
