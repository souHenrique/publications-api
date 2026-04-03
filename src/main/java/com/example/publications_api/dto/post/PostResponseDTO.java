package com.example.publications_api.dto.post;

import java.time.LocalDateTime;

public record PostResponseDTO(String username, String text, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
