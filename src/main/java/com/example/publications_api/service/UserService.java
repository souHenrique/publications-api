package com.example.publications_api.service;

import com.example.publications_api.dto.user.UserRequestDTO;
import com.example.publications_api.dto.user.UserResponseDTO;
import com.example.publications_api.model.Comment;
import com.example.publications_api.model.Post;
import com.example.publications_api.model.User;
import com.example.publications_api.repository.UserRepository;
import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        User user = new User();
        user.setUsername(userRequestDTO.username());
        user.setName(userRequestDTO.name());
        user.setEmail(userRequestDTO.email());
        user.setPassword(userRequestDTO.password());
        user.setBiography(userRequestDTO.biography());

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getUsername(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getBiography()
        );
    }

    public Optional<User> findUserById(Long idUser) {
        return userRepository.findUserByIdUser(idUser);
    }

    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO, Long idUser) {

        User existingUser = userRepository.findUserByIdUser(idUser)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        existingUser.setUsername(userRequestDTO.username());
        existingUser.setName(userRequestDTO.name());
        existingUser.setEmail(userRequestDTO.email());
        existingUser.setPassword(userRequestDTO.password());
        existingUser.setBiography(userRequestDTO.biography());

        return new UserResponseDTO(
                existingUser.getUsername(),
                existingUser.getName(),
                existingUser.getEmail(),
                existingUser.getBiography()
        );
    }

    public void deleteUser(Long idUser) {

        User existingUser = userRepository.findUserByIdUser(idUser)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        userRepository.delete(existingUser);
    }

    public List<Post> findAllPublicPostsFromUser(Long idUser) {
        return userRepository.findAllPublicPostsFromUser(idUser);
    }

    public List<Comment> findAllCommentOnPublicPostFromUser(Long idUser) {
        return userRepository.findAllCommentOnPublicPostFromUser(idUser);
    };
}
