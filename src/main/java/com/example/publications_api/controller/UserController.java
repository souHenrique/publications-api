package com.example.publications_api.controller;

import com.example.publications_api.dto.comment.CommentResponseDTO;
import com.example.publications_api.dto.post.PostRequestDTO;
import com.example.publications_api.dto.post.PostResponseDTO;
import com.example.publications_api.dto.user.UserRequestDTO;
import com.example.publications_api.dto.user.UserResponseDTO;
import com.example.publications_api.model.Comment;
import com.example.publications_api.model.Post;
import com.example.publications_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{idUser}")
    public ResponseEntity<List<PostResponseDTO>> findAllPublicPostsByUser(@PathVariable Long idUser) {
        List<PostResponseDTO> userResponseDTOList = userService.findAllPublicPostsFromUser(idUser);
        return ResponseEntity.ok(userResponseDTOList);
    }

    @GetMapping("/{idComment}")
    public ResponseEntity<List<CommentResponseDTO>> findAllCommentsOnPublicPostsByUser(@PathVariable Long idUser) {
        List<CommentResponseDTO> userResponseDTOList = userService.findAllCommentOnPublicPostFromUser(idUser);
        return ResponseEntity.ok(userResponseDTOList);
    }
}
