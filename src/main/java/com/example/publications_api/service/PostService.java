package com.example.publications_api.service;

import com.example.publications_api.dto.post.PostRequestDTO;
import com.example.publications_api.dto.post.PostResponseDTO;
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
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

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
                .orElseThrow(() -> new RuntimeException("Publicação não encontrada!"));
    }

    public PostResponseDTO updatePost(PostRequestDTO postRequestDTO, Long idPost, Long idUser) {

        Post existingPost = postRepository.findById(idPost)
                .orElseThrow(() -> new RuntimeException("Publicação não encontrada!"));

        User existingUser = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

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

    public PostResponseDTO deletePost(Long idPost) {

        Post existingPost = postRepository.findById(idPost)
                .orElseThrow(() -> new RuntimeException("Publicação não encontrada!"));
        postRepository.delete(existingPost);
        return null;
    }

    public PostResponseDTO archivePost(Long idPost) {

        Post existingPost = postRepository.findById(idPost)
                .orElseThrow(() -> new RuntimeException("Publicação não encontrada!"));
        existingPost.setArchived(true);
        postRepository.save(existingPost);
        return null;
    }

    public List<Comment> findAllCommentsByPost(Long idPost) {
        return postRepository.findAllCommentsByPost(idPost);
    }

}
