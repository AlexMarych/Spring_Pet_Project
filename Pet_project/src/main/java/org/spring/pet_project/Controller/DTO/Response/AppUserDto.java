package org.spring.pet_project.Controller.DTO.Response;

import java.util.UUID;

public record AppUserDto(
        UUID id,
        String name,
        String email
) {

}
