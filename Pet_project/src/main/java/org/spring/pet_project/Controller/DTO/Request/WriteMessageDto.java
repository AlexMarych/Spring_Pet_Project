package org.spring.pet_project.Controller.DTO.Request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record WriteMessageDto(
        @NotBlank
        @Min(1)
        @Max(200)
        String text,

        UUID taskId,
        UUID chatId
){

}
