package org.spring.pet_project.Controller.DTO.Response;

import org.spring.pet_project.Model.Enumeration.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public record CheckStatusDto(
        UUID id,
        LocalDateTime timeOfCheck,
        Status status
) {
}
