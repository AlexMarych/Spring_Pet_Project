package org.spring.pet_project.Controller.DTO.Request.Create;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateChatMessageDto(
        @NotBlank
        @Min(1)
        @Max(200)
        String text,

        @NotNull
        UUID chatId
){

}
