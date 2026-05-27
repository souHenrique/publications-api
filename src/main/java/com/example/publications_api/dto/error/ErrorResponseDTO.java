package com.example.publications_api.dto.error;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        int status,
        String error,
        String message,
        LocalDateTime timestamp
) {
}
