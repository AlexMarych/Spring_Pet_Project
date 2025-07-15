package org.spring.pet_project.Controller.DTO.Response;

import java.util.Set;
import java.util.UUID;

public record ChatDto(
        UUID id,
        String name,
        UUID boardId,
        Set<UUID> messageIds
) {
}
