package com.example.publications_api.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostRequestDTO(

        @NotNull(message = "O ID do usuário é obrigatório.")
        Long userId,

        @NotBlank(message = "É obrigatório escrever um texto.")
        String text){
}
