package org.spring.pet_project.Handler;

import java.time.LocalDateTime;

public record ErrorResponse(
        int statusCode,
        String code,
        String description,
        LocalDateTime timestamp
) {
}
