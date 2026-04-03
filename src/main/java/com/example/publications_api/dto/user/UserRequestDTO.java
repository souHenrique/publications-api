package com.example.publications_api.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(

        @NotBlank(message = "Username não pode estar vazio.")
        String username,

        @NotBlank(message = "O nome não pode estar vazio.")
        String name,

        @NotBlank(message = "O email não pode estar vazio.")
        @Email(message = "Digite um formato válido de email.")
        String email,

        @NotBlank(message = "A senha não pode estar vazia")
        @Size(min = 6, message = "A senha deve conter no mínimo 6 caracteres.")
        String password,

        @Size(max = 500, message = "Biografia não pode exceder 500 caracteres.")
        String biography) {
}
