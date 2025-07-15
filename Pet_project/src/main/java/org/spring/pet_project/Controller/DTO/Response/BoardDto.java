package org.spring.pet_project.Controller.DTO.Response;

import java.util.Set;
import java.util.UUID;

public record BoardDto(
        UUID id,
        String title,
        String description,
        Set<UUID> listsOfTasksIds,
        Set<UUID> chatIds,
        UUID ownerAppUserId,
        Set<UUID> memberAppUserIds
) {
}
