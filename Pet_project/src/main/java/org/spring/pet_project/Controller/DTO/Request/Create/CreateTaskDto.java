package org.spring.pet_project.Controller.DTO.Request.Create;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record CreateTaskDto(
        @NotBlank
        @Min(2)
        @Max(20)
        String name,
        @NotEmpty
        @Max(500)
        String description,
        @NotNull
        LocalDateTime dueDate,
        @NotNull
        UUID listOfTasksId,
        Set<UUID> memberIds
) {
}
