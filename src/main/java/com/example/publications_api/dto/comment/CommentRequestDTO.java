package com.example.publications_api.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CommentRequestDTO(

        @NotNull(message = "O ID do usuário é obrigatório.")
        Long userId,

        @NotNull(message = "O ID da publicação é obrigatório.")
        Long postId,

        @NotBlank(message = "É obrigatório escrever um comentário.")
        @Size(min = 1, max = 5000, message = "A mensagem deve ter entre 1 e 5000 caracteres.")
        String message) {
}
