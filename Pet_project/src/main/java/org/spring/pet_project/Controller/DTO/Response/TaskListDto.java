package org.spring.pet_project.Controller.DTO.Response;

import org.spring.pet_project.Model.Task;

import java.util.Set;
import java.util.UUID;

public record TaskListDto(
        UUID id,
        String title,
        Set<UUID> taskIds
) {
}
