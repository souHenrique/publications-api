package com.example.publications_api.service;

import com.example.publications_api.dto.comment.CommentResponseDTO;
import com.example.publications_api.dto.post.PostRequestDTO;
import com.example.publications_api.dto.post.PostResponseDTO;
import com.example.publications_api.exceptions.ResourceNotFoundException;
import com.example.publications_api.exceptions.UnauthorizedException;
import com.example.publications_api.model.Comment;
import com.example.publications_api.model.Post;
import com.example.publications_api.model.User;
import com.example.publications_api.repository.PostRepository;
import com.example.publications_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    PostService (PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public PostResponseDTO createPost(PostRequestDTO postRequestDTO) {

        Post post = new Post();

        User existingUser = userRepository.findById(postRequestDTO.userId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));

        post.setUserId(existingUser);
        post.setText(postRequestDTO.text());

        Post savedPost = postRepository.save(post);

        return new PostResponseDTO(
                existingUser.getUsername(),
                savedPost.getText(),
                savedPost.getCreatedAt(),
                savedPost.getUpdatedAt()
        );
    }

    public PostResponseDTO findPostById(Long idPost) {
        return postRepository.findById(idPost)
                .map(post -> new PostResponseDTO(
                        post.getUserId().getUsername(),
                        post.getText(),
                        post.getCreatedAt(),
                        post.getUpdatedAt()
                ))
                .orElseThrow(() -> new ResourceNotFoundException("Publicação não encontrada!"));
    }

    public PostResponseDTO updatePost(PostRequestDTO postRequestDTO, Long idPost, Long idUser) {

        Post existingPost = postRepository.findById(idPost)
                .orElseThrow(() -> new ResourceNotFoundException("Publicação não encontrada!"));

        User existingUser = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));

        if (!existingPost.getUserId().getIdUser().equals(idUser)) {
            throw new UnauthorizedException("Você não tem permissão para editar esta publicação.");
        }

        existingPost.setText(postRequestDTO.text());
        postRepository.save(existingPost);

        Post savedPost = postRepository.save(existingPost);

        return new PostResponseDTO(
                existingUser.getUsername(),
                savedPost.getText(),
                savedPost.getCreatedAt(),
                savedPost.getUpdatedAt()
        );
    }

    public PostResponseDTO deletePost(Long idPost, Long idUser) {

        Post existingPost = postRepository.findById(idPost)
                .orElseThrow(() -> new ResourceNotFoundException("Publicação não encontrada!"));

        User existingUser = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        if (!existingPost.getUserId().getIdUser().equals(idUser)) {
            throw new UnauthorizedException("Você não tem permissão para deletar esta publicação.");
        }

        postRepository.delete(existingPost);
        return null;
    }

    public PostResponseDTO archivePost(Long idPost, Long idUser) {

        Post existingPost = postRepository.findById(idPost)
                .orElseThrow(() -> new ResourceNotFoundException("Publicação não encontrada!"));

        User existingUser = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));

        if (!existingPost.getUserId().getIdUser().equals(idUser)) {
            throw new UnauthorizedException("Você não tem permissão para arquivar esta publicação.");
        }

        existingPost.setArchived(true);
        postRepository.save(existingPost);
        return null;
    }

    public List<CommentResponseDTO> findAllCommentsByPost(Long idPost) {
        return postRepository.findAllCommentsByPost(idPost);
    }
}
