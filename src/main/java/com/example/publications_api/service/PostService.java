package com.example.publications_api.service;

import com.example.publications_api.dto.post.PostRequestDTO;
import com.example.publications_api.dto.post.PostResponseDTO;
import com.example.publications_api.model.Post;
import com.example.publications_api.model.User;
import com.example.publications_api.repository.PostRepository;
import com.example.publications_api.repository.UserRepository;

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


}
