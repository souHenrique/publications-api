package com.example.publications_api.dto.post;

import jakarta.validation.constraints.NotBlank;

public record PostRequestDTO(

        @NotBlank(message = "O ID do usuário é obrigatório.")
        Long userId,

        @NotBlank(message = "É obrigatório escrever um texto.")
        String text){
}
