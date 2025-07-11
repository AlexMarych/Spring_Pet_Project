package org.spring.pet_project.Controller.DTO.Request;

import jakarta.validation.constraints.NotNull;
import org.spring.pet_project.Model.Enumeration.Status;

import java.util.UUID;

public record UpdateCheckStatusDto(
        @NotNull
        UUID id,
        Status status
) {
}
