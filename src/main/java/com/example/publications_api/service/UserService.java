package com.example.publications_api.service;

import com.example.publications_api.dto.user.UserRequestDTO;
import com.example.publications_api.dto.user.UserResponseDTO;
import com.example.publications_api.model.User;
import com.example.publications_api.repository.UserRepository;

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

    public void findUserById(Long idUser) {
        userRepository.findUserByIdUser(idUser);
    }
}
