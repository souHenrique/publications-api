package com.example.publications_api.controller;

import com.example.publications_api.dto.comment.CommentResponseDTO;
import com.example.publications_api.dto.post.PostResponseDTO;
import com.example.publications_api.dto.user.UserRequestDTO;
import com.example.publications_api.dto.user.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.publications_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Operações relacionadas a usuários")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Criar usuário", description = "Cria um novo usuário no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou usuário já existente")
    })
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.createUser(userRequestDTO);
        return ResponseEntity.status(201).body(userResponseDTO);
    }

    @GetMapping("/{idUser}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna os dados de um usuário a partir do ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable Long idUser) {
        UserResponseDTO userResponseDTO = userService.findUserById(idUser);
        return ResponseEntity.ok(userResponseDTO);
    }

    @PutMapping("/{idUser}")
    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou conflito de email/username"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody @Valid UserRequestDTO userRequestDTO, @PathVariable Long idUser) {
        UserResponseDTO userResponseDTO = userService.updateUser(userRequestDTO, idUser);
        return ResponseEntity.ok(userResponseDTO);
    }

    @DeleteMapping("/{idUser}")
    @Operation(summary = "Deletar usuário", description = "Remove um usuário do sistema pelo ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable Long idUser) {
        UserResponseDTO userResponseDTO = userService.deleteUser(idUser);
        return ResponseEntity.ok(userResponseDTO);
    }

    @GetMapping("/{idUser}/posts")
    @Operation(summary = "Listar posts públicos do usuário", description = "Retorna todas as publicações públicas de um usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posts retornados com sucesso")
    })
    public ResponseEntity<List<PostResponseDTO>> findAllPublicPostsByUser(@PathVariable Long idUser) {
        List<PostResponseDTO> userResponseDTOList = userService.findAllPublicPostsFromUser(idUser);
        return ResponseEntity.ok(userResponseDTOList);
    }

    @GetMapping("/{idUser}/comments")
    @Operation(summary = "Listar comentários do usuário em posts públicos", description = "Retorna todos os comentários feitos por um usuário em posts públicos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentários retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<List<CommentResponseDTO>> findAllCommentsByUserOnPublicPosts(@PathVariable Long idUser) {
        List<CommentResponseDTO> userResponseDTOList = userService.findAllCommentByUserOnPublicPosts(idUser);
        return ResponseEntity.ok(userResponseDTOList);
    }
}
