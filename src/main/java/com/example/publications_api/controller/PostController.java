package com.example.publications_api.controller;

import com.example.publications_api.dto.comment.CommentResponseDTO;
import com.example.publications_api.dto.post.PostRequestDTO;
import com.example.publications_api.dto.post.PostResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.publications_api.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Tag(name = "Posts", description = "Operações relacionadas a publicações")
public class PostController {

    private final PostService postService;

    @PostMapping
    @Operation(summary = "Criar publicação", description = "Cria uma nova publicação para um usuário existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Publicação criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody @Valid PostRequestDTO postRequestDTO) {
        PostResponseDTO postResponseDTO = postService.createPost(postRequestDTO);
        return ResponseEntity.status(201).body(postResponseDTO);
    }

    @GetMapping("/{idPost}")
    @Operation(summary = "Buscar publicação por ID", description = "Retorna os dados de uma publicação pelo ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publicação encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Publicação não encontrada")
    })
    public ResponseEntity<PostResponseDTO> findPostById(@PathVariable Long idPost) {
        PostResponseDTO postResponseDTO = postService.findPostById(idPost);
        return ResponseEntity.ok(postResponseDTO);
    }

    @PutMapping("/{idPost}/{idUser}")
    @Operation(summary = "Atualizar publicação", description = "Atualiza uma publicação existente validando a posse do recurso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publicação atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "403", description = "Usuário sem permissão para editar a publicação"),
            @ApiResponse(responseCode = "404", description = "Publicação ou usuário não encontrado")
    })
    public ResponseEntity<PostResponseDTO> updatePost(@RequestBody @Valid PostRequestDTO postRequestDTO, @PathVariable Long idPost, @PathVariable Long idUser) {
        PostResponseDTO postResponseDTO = postService.updatePost(postRequestDTO, idPost, idUser);
        return ResponseEntity.ok(postResponseDTO);
    }

    @DeleteMapping("/{idPost}/{idUser}")
    @Operation(summary = "Deletar publicação", description = "Remove uma publicação existente validando a posse do recurso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publicação removida com sucesso"),
            @ApiResponse(responseCode = "403", description = "Usuário sem permissão para deletar a publicação"),
            @ApiResponse(responseCode = "404", description = "Publicação ou usuário não encontrado")
    })
    public ResponseEntity<PostResponseDTO> deletePost(@PathVariable Long idPost, @PathVariable Long idUser) {
        PostResponseDTO postResponseDTO = postService.deletePost(idPost, idUser);
        return ResponseEntity.ok(postResponseDTO);
    }

    @PatchMapping("/{idPost}/{idUser}")
    @Operation(summary = "Arquivar publicação", description = "Arquiva uma publicação existente validando a posse do recurso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publicação arquivada com sucesso"),
            @ApiResponse(responseCode = "403", description = "Usuário sem permissão para arquivar a publicação"),
            @ApiResponse(responseCode = "404", description = "Publicação ou usuário não encontrado")
    })
    public ResponseEntity<PostResponseDTO> archivePost(@PathVariable Long idPost, @PathVariable Long idUser) {
        PostResponseDTO postResponseDTO = postService.archivePost(idPost, idUser);
        return ResponseEntity.ok(postResponseDTO);
    }

    @GetMapping("/{idPost}/comments")
    @Operation(summary = "Listar comentários da publicação", description = "Retorna todos os comentários associados a uma publicação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentários retornados com sucesso")
    })
    public ResponseEntity<List<CommentResponseDTO>> findAllCommentsByPost(@PathVariable Long idPost) {
        List<CommentResponseDTO> postResponseDTOList = postService.findAllCommentsByPost(idPost);
        return ResponseEntity.ok(postResponseDTOList);
    }
}
