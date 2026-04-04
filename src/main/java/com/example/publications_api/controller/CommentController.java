package com.example.publications_api.controller;

import com.example.publications_api.dto.comment.CommentRequestDTO;
import com.example.publications_api.dto.comment.CommentResponseDTO;
import com.example.publications_api.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    public final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO commentResponseDTO = commentService.createComment(commentRequestDTO);
        return ResponseEntity.status(201).body(commentResponseDTO);
    }

    @PutMapping("/{idComment}/{idPost}/{idUser}")
    public ResponseEntity<CommentResponseDTO> updateComment(@RequestBody CommentRequestDTO commentRequestDTO, @PathVariable Long idComment, @PathVariable Long idPost, Long idUser) {
        CommentResponseDTO commentResponseDTO = commentService.updateComment(commentRequestDTO, idComment, idPost, idUser);
        return ResponseEntity.ok(commentResponseDTO);
    }

    @DeleteMapping("/{idComment}")
    public ResponseEntity<CommentResponseDTO> deleteComment(@PathVariable Long idComment) {
        CommentResponseDTO commentResponseDTO = commentService.deleteComment(idComment);
        return ResponseEntity.ok(commentResponseDTO);
    }

     //depois pesquisar como fazer para listar os comentários com os parâmetros
}
