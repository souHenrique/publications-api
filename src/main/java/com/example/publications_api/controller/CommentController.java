package com.example.publications_api.controller;

import com.example.publications_api.dto.comment.CommentRequestDTO;
import com.example.publications_api.dto.comment.CommentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.publications_api.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Tag(name = "Comments", description = "Operações relacionadas a comentários")
public class CommentController {

    public final CommentService commentService;

    @PostMapping
    @Operation(summary = "Criar comentário", description = "Cria um novo comentário em uma publicação existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comentário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuário ou publicação não encontrado")
    })
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody @Valid CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO commentResponseDTO = commentService.createComment(commentRequestDTO);
        return ResponseEntity.status(201).body(commentResponseDTO);
    }

    @PutMapping("/{idComment}/{idPost}/{idUser}")
    @Operation(summary = "Atualizar comentário", description = "Atualiza um comentário existente validando a posse do recurso e a publicação informada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou comentário não pertence à publicação"),
            @ApiResponse(responseCode = "403", description = "Usuário sem permissão para editar o comentário"),
            @ApiResponse(responseCode = "404", description = "Comentário, publicação ou usuário não encontrado")
    })
    public ResponseEntity<CommentResponseDTO> updateComment(@RequestBody @Valid CommentRequestDTO commentRequestDTO, @PathVariable Long idComment, @PathVariable Long idPost, @PathVariable Long idUser) {
        CommentResponseDTO commentResponseDTO = commentService.updateComment(commentRequestDTO, idComment, idPost, idUser);
        return ResponseEntity.ok(commentResponseDTO);
    }

    @DeleteMapping("/{idComment}/{idUser}")
    @Operation(summary = "Deletar comentário", description = "Remove um comentário existente validando a posse do recurso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentário removido com sucesso"),
            @ApiResponse(responseCode = "403", description = "Usuário sem permissão para deletar o comentário"),
            @ApiResponse(responseCode = "404", description = "Comentário ou usuário não encontrado")
    })
    public ResponseEntity<CommentResponseDTO> deleteComment(@PathVariable Long idComment, @PathVariable Long idUser) {
        CommentResponseDTO commentResponseDTO = commentService.deleteComment(idComment, idUser);
        return ResponseEntity.ok(commentResponseDTO);
    }

}
