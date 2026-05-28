package com.example.publications_api.service;

import com.example.publications_api.dto.comment.CommentResponseDTO;
import com.example.publications_api.dto.post.PostResponseDTO;
import com.example.publications_api.dto.user.UserRequestDTO;
import com.example.publications_api.dto.user.UserResponseDTO;
import com.example.publications_api.exceptions.BusinessException;
import com.example.publications_api.exceptions.ResourceNotFoundException;
import com.example.publications_api.model.User;
import com.example.publications_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        if (userRepository.existsByUsername(userRequestDTO.username())) {
            throw new BusinessException("Username já está em uso!");
        }

        if (userRepository.existsByEmail(userRequestDTO.email())) {
            throw new BusinessException("Email já está em uso!");
        }

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
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));
    }

    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO, Long idUser) {

        User existingUser = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));

        userRepository.findByEmail(userRequestDTO.email())
                        .ifPresent(user -> {
                            if (!user.getIdUser().equals(idUser)) {
                                throw new BusinessException("Email já está em uso!");
                            }
                        });

        userRepository.findByUsername(userRequestDTO.username())
                .ifPresent(user -> {
                    if (!user.getIdUser().equals(idUser)) {
                        throw new BusinessException("Username já está em uso!");
                    }
                });

        existingUser.setUsername(userRequestDTO.username());
        existingUser.setName(userRequestDTO.name());
        existingUser.setEmail(userRequestDTO.email());

        String hashPassword = passwordEncoder.encode(userRequestDTO.password());
        existingUser.setPassword(hashPassword);

        existingUser.setBiography(userRequestDTO.biography());

        User savedUser = userRepository.save(existingUser);

        return new UserResponseDTO(
                savedUser.getUsername(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getBiography()
        );
    }

    public UserResponseDTO deleteUser(Long idUser) {

        User existingUser = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));
        userRepository.delete(existingUser);
        return null;
    }

    public List<PostResponseDTO> findAllPublicPostsFromUser(Long idUser) {
        return userRepository.findAllPublicPostsFromUser(idUser);
    }

    public List<CommentResponseDTO> findAllCommentByUserOnPublicPosts(Long idUser) {
        userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException(("Usuário não encontrado!")));
        return userRepository.findAllCommentByUserOnPublicPosts(idUser);
    };
}
