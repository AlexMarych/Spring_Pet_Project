package org.spring.pet_project.Controller.DTO.Response;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AppUserDto(
        UUID id,
        String name,
        String email
) {


}
