package com.example.publications_api.controller;

import com.example.publications_api.dto.comment.CommentResponseDTO;
import com.example.publications_api.dto.post.PostRequestDTO;
import com.example.publications_api.dto.post.PostResponseDTO;
import com.example.publications_api.model.Comment;
import com.example.publications_api.model.Post;
import com.example.publications_api.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO postRequestDTO) {
        PostResponseDTO postResponseDTO = postService.createPost(postRequestDTO);
        return ResponseEntity.status(201).body(postResponseDTO);
    }

    @GetMapping("/{idPost}")
    public ResponseEntity<PostResponseDTO> findPostById(@PathVariable Long idPost) {
        PostResponseDTO postResponseDTO = postService.findPostById(idPost);
        return ResponseEntity.ok(postResponseDTO);
    }

    @PutMapping("/{idPost}/{idUser}")
    public ResponseEntity<PostResponseDTO> updatePost(@RequestBody @Valid PostRequestDTO postRequestDTO, @PathVariable Long idPost, @PathVariable Long idUser) {
        PostResponseDTO postResponseDTO = postService.updatePost(postRequestDTO, idPost, idUser);
        return ResponseEntity.ok(postResponseDTO);
    }

    @DeleteMapping("/{idPost}")
    public ResponseEntity<PostResponseDTO> deletePost(@PathVariable Long idPost) {
        PostResponseDTO postResponseDTO = postService.deletePost(idPost);
        return ResponseEntity.ok(postResponseDTO);
    }

    @PatchMapping("/{idPost}")
    public ResponseEntity<PostResponseDTO> archivePost(@PathVariable Long idPost) {
        PostResponseDTO postResponseDTO = postService.archivePost(idPost);
        return ResponseEntity.ok(postResponseDTO);
    }

    @GetMapping("/{idPost}")
    public ResponseEntity<List<CommentResponseDTO>> findAllCommentsByPost(@PathVariable Long idPost) {
        List<CommentResponseDTO> postResponseDTOList = postService.findAllCommentsByPost(idPost);
        return ResponseEntity.ok(postResponseDTOList);
    }
}
