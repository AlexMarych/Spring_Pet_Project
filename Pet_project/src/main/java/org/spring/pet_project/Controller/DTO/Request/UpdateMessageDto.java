package org.spring.pet_project.Controller.DTO.Request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateMessageDto(
        @NotNull
        UUID id,
        @NotBlank
        @Min(2)
        @Max(200)
        String text

) {
}
