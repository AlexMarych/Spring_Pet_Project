package org.spring.pet_project.Controller.DTO.Request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.spring.pet_project.Config.XOR;

import java.util.UUID;

@XOR(fields = { "taskId", "chatId" })
public record WriteMessageDto(
        @NotBlank
        @Min(1)
        @Max(200)
        String text,

        UUID taskId,
        UUID chatId
){

}
