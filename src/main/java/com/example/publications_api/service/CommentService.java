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

        Post existingPost = postRepository.findById(commentRequestDTO.postId())
                .orElseThrow(() -> new RuntimeException("Publicação não encontrada!"));

        comment.setUserId(existingUser);
        comment.setPostId(existingPost);
        comment.setMessage(commentRequestDTO.message());

        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDTO(
                existingUser.getUsername(),
                savedComment.getMessage(),
                savedComment.getCreatedAt(),
                savedComment.getUpdatedAt()
        );
    }

    public CommentResponseDTO updateComment(CommentRequestDTO commentRequestDTO, Long idComment, Long idPost, Long idUser) {

        Comment existingComment = commentRepository.findById(idComment)
                .orElseThrow(() -> new RuntimeException("Comentário não encontrado!"));

        Post existingPost = postRepository.findById(idPost)
                .orElseThrow(() -> new RuntimeException("Publicação não encontrada!"));

        User existingUser = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        existingComment.setMessage(commentRequestDTO.message());

        Comment updatedComment = commentRepository.save(existingComment);

        return new CommentResponseDTO(
                existingUser.getUsername(),
                updatedComment.getMessage(),
                updatedComment.getCreatedAt(),
                updatedComment.getUpdatedAt()
        );
    }

    public CommentResponseDTO deleteComment(Long idComment) {
        Comment existingComment = commentRepository.findCommentByIdComment(idComment)
                .orElseThrow(() -> new RuntimeException("Comentário não encontrado!"));
        commentRepository.delete(existingComment);
        return null;
    }
}
