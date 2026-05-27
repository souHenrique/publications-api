package com.example.publications_api.service;

import com.example.publications_api.dto.comment.CommentRequestDTO;
import com.example.publications_api.dto.comment.CommentResponseDTO;
import com.example.publications_api.exceptions.ResourceNotFoundException;
import com.example.publications_api.model.Comment;
import com.example.publications_api.model.Post;
import com.example.publications_api.model.User;
import com.example.publications_api.repository.CommentRepository;
import com.example.publications_api.repository.PostRepository;
import com.example.publications_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    public final CommentRepository commentRepository;
    public final UserRepository userRepository;
    public final PostRepository postRepository;

    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO) {

        Comment comment = new Comment();

        User existingUser = userRepository.findById(commentRequestDTO.userId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));

        Post existingPost = postRepository.findById(commentRequestDTO.postId())
                .orElseThrow(() -> new ResourceNotFoundException("Publicação não encontrada!"));

        comment.setUserId(existingUser);
        comment.setPostId(existingPost);
        comment.setMessage(commentRequestDTO.message());

        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDTO(
                savedComment.getIdComment(),
                savedComment.getPostId().getIdPost(),
                savedComment.getUserId().getUsername(),
                savedComment.getMessage(),
                savedComment.getCreatedAt(),
                savedComment.getUpdatedAt()
        );
    }

    public CommentResponseDTO updateComment(CommentRequestDTO commentRequestDTO, Long idComment, Long idPost, Long idUser) {

        Comment existingComment = commentRepository.findById(idComment)
                .orElseThrow(() -> new ResourceNotFoundException("Comentário não encontrado!"));

        Post existingPost = postRepository.findById(idPost)
                .orElseThrow(() -> new ResourceNotFoundException("Publicação não encontrada!"));

        User existingUser = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));

        existingComment.setMessage(commentRequestDTO.message());

        Comment updatedComment = commentRepository.save(existingComment);

        return new CommentResponseDTO(
                existingComment.getIdComment(),
                existingPost.getIdPost(),
                existingUser.getUsername(),
                updatedComment.getMessage(),
                updatedComment.getCreatedAt(),
                updatedComment.getUpdatedAt()
        );
    }

    public CommentResponseDTO deleteComment(Long idComment) {
        Comment existingComment = commentRepository.findCommentByIdComment(idComment)
                .orElseThrow(() -> new ResourceNotFoundException("Comentário não encontrado!"));
        commentRepository.delete(existingComment);
        return null;
    }
}
