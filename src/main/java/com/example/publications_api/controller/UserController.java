package com.example.publications_api.controller;

import com.example.publications_api.dto.user.UserRequestDTO;
import com.example.publications_api.dto.user.UserResponseDTO;
import com.example.publications_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.createUser(userRequestDTO);
        return ResponseEntity.status(201).body(userResponseDTO);
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable Long idUser) {
        UserResponseDTO userResponseDTO = userService.findUserById(idUser);
        return ResponseEntity.ok(userResponseDTO);
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody @Valid UserRequestDTO userRequestDTO, @PathVariable Long idUser) {
        UserResponseDTO userResponseDTO = userService.updateUser(userRequestDTO, idUser);
        return ResponseEntity.ok(userResponseDTO);
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable Long idUser) {
        UserResponseDTO userResponseDTO = userService.deleteUser(idUser);
        return ResponseEntity.ok(userResponseDTO);
    }

    //depois pesquisar como fazer para listar os usuários com os parâmetros
}
