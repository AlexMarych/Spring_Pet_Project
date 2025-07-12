package org.spring.pet_project.Controller.DTO.Response;

import java.time.LocalDateTime;
import java.util.UUID;

public record MessageDto(
        UUID id,
        String text,
        LocalDateTime TimeOfSend
) {
}
