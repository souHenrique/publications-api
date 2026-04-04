package com.example.publications_api.service;

import com.example.publications_api.dto.comment.CommentRequestDTO;
import com.example.publications_api.dto.comment.CommentResponseDTO;
import com.example.publications_api.model.Comment;
import com.example.publications_api.model.Post;
import com.example.publications_api.model.User;
import com.example.publications_api.repository.CommentRepository;
import com.example.publications_api.repository.PostRepository;
import com.example.publications_api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    public final CommentRepository commentRepository;
    public final UserRepository userRepository;
    public final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO) {

        Comment comment = new Comment();

        User existingUser = userRepository.findById(commentRequestDTO.userId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        Post existingPost = postRepository.findPostByIdPost(commentRequestDTO.postId())
                .orElseThrow(() -> new RuntimeException("Publicação não encontrada!"));

        comment.setUserId(existingUser);
        comment.setPostId(existingPost);
        comment.setMessage(commentRequestDTO.message());

        return new CommentResponseDTO(
                existingUser.getUsername(),
                comment.getMessage(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
