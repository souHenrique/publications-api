package com.example.publications_api.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PostRequestDTO(

        @NotNull(message = "O ID do usuário é obrigatório.")
        Long userId,

        @NotBlank(message = "É obrigatório escrever um texto.")
        @Size(min = 1, max = 5000, message = "O texto deve ter entre 1 e 5000 caracteres.")
        String text){
}
