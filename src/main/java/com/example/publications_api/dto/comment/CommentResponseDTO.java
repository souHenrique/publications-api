package com.example.publications_api.dto.comment;

import java.time.LocalDateTime;

public record CommentResponseDTO(
        Long commentId,
        Long postId,
        String username,
        String message,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
