package com.example.publications_api.dto.comment;

public record CommentRequestDTO(Long userId, Long postId, String message) {
}
