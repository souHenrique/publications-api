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
import java.util.Optional;

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

        User existingUser = userRepository.findUserByIdUser(postRequestDTO.userId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        post.setUserId(existingUser);
        post.setText(postRequestDTO.text());

        return new PostResponseDTO(
                existingUser.getUsername(),
                post.getText(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }

    public Optional<Post> findPostById(Long idPost) {
        return postRepository.findPostByIdPost(idPost);
    }

    public PostResponseDTO updatePost(PostRequestDTO postRequestDTO, Long idPost, Long idUser) {

        Post existingPost = postRepository.findPostByIdPost(idPost)
                .orElseThrow(() -> new RuntimeException("Publicação não encontrada!"));

        User existingUser = userRepository.findUserByIdUser(existingPost.getUserId().getIdUser())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        existingPost.setText(postRequestDTO.text());
        postRepository.save(existingPost);

        return new PostResponseDTO(
                existingUser.getUsername(),
                existingPost.getText(),
                existingPost.getCreatedAt(),
                existingPost.getUpdatedAt()
        );
    }

    public void deletePost(Long idPost) {

        Post existingPost = postRepository.findPostByIdPost(idPost)
                .orElseThrow(() -> new RuntimeException("Publicação não encontrada!"));
        postRepository.delete(existingPost);
    }

    public void archivePost(Long idPost) {

        Post existingPost = postRepository.findPostByIdPost(idPost)
                .orElseThrow(() -> new RuntimeException("Publicação não encontrada!"));
        existingPost.setArchived(true);
        postRepository.save(existingPost);
    }

    public List<Comment> findAllCommentsByPost(Long idPost) {
        return postRepository.findAllCommentsByPost(idPost);
    }

}
