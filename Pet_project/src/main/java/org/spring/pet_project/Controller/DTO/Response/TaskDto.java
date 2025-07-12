package org.spring.pet_project.Controller.DTO.Response;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String name,
        String description,
        LocalDateTime dueDate,
        Set<UUID> checkStatusIds,
        Set<UUID> messageIds
) {
}
