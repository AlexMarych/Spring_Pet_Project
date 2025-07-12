package org.spring.pet_project.Controller.DTO.Response;

import java.util.Set;
import java.util.UUID;

public record BoardDto(
        UUID id,
        String title,
        String description,
        Set<UUID> listsOfTasks,
        Set<UUID> chats,
        UUID ownerAppUser,
        Set<UUID> memberAppUsers
) {
}
