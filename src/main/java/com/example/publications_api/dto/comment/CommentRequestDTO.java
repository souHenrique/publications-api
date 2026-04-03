package com.example.publications_api.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentRequestDTO(

        @NotNull(message = "O ID do usuário é obrigatório.")
        Long userId,

        @NotNull(message = "O ID da publicação é obrigatório.")
        Long postId,

        @NotBlank(message = "É obrigatório escrever um comentário.")
        String message) {
}
