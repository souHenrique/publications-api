package com.example.publications_api.dto.comment;

import jakarta.validation.constraints.NotBlank;

public record CommentRequestDTO(

        @NotBlank(message = "O ID do usuário é obrigatório.")
        Long userId,

        @NotBlank(message = "O ID da publicação é obrigatório.")
        Long postId,

        @NotBlank(message = "É obrigatório escrever um comentário.")
        String message) {
}
