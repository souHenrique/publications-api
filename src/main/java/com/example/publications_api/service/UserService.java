package com.example.publications_api.service;

import com.example.publications_api.dto.user.UserRequestDTO;
import com.example.publications_api.dto.user.UserResponseDTO;
import com.example.publications_api.model.Comment;
import com.example.publications_api.model.Post;
import com.example.publications_api.model.User;
import com.example.publications_api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    UserService (UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        User user = new User();

        user.setUsername(userRequestDTO.username());
        user.setName(userRequestDTO.name());
        user.setEmail(userRequestDTO.email());

        String hashPassword = passwordEncoder.encode(userRequestDTO.password());
        user.setPassword(hashPassword);

        user.setBiography(userRequestDTO.biography());

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getUsername(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getBiography()
        );
    }

    public UserResponseDTO findUserById(Long idUser) {

        return userRepository.findById(idUser)
                .map(user -> new UserResponseDTO(
                        user.getUsername(),
                        user.getName(),
                        user.getEmail(),
                        user.getBiography()
                ))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }

    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO, Long idUser) {

        User existingUser = userRepository.findById(idUser)
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

    public UserResponseDTO deleteUser(Long idUser) {

        User existingUser = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        userRepository.delete(existingUser);
        return null;
    }

    public List<Post> findAllPublicPostsFromUser(Long idUser) {
        return userRepository.findAllPublicPostsFromUser(idUser);
    }

    public List<Comment> findAllCommentOnPublicPostFromUser(Long idUser) {
        return userRepository.findAllCommentOnPublicPostFromUser(idUser);
    };
}
