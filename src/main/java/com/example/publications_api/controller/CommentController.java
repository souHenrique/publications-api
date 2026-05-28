package com.example.publications_api.controller;

import com.example.publications_api.dto.comment.CommentRequestDTO;
import com.example.publications_api.dto.comment.CommentResponseDTO;
import com.example.publications_api.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    public final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody @Valid CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO commentResponseDTO = commentService.createComment(commentRequestDTO);
        return ResponseEntity.status(201).body(commentResponseDTO);
    }

    @PutMapping("/{idComment}/{idPost}/{idUser}")
    public ResponseEntity<CommentResponseDTO> updateComment(@RequestBody @Valid CommentRequestDTO commentRequestDTO, @PathVariable Long idComment, @PathVariable Long idPost, @PathVariable Long idUser) {
        CommentResponseDTO commentResponseDTO = commentService.updateComment(commentRequestDTO, idComment, idPost, idUser);
        return ResponseEntity.ok(commentResponseDTO);
    }

    @DeleteMapping("/{idComment}/{idUser}")
    public ResponseEntity<CommentResponseDTO> deleteComment(@PathVariable Long idComment, @PathVariable Long idUser) {
        CommentResponseDTO commentResponseDTO = commentService.deleteComment(idComment, idUser);
        return ResponseEntity.ok(commentResponseDTO);
    }

}
