package com.alkateca.login.service;

import com.alkateca.login.dto.UserRequestDTO;
import com.alkateca.login.dto.UserResponseDTO;
import com.alkateca.login.model.User;
import com.alkateca.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.ClassUtils.isPresent;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserRequestDTO userRequestDTO) {
        if(userRepository.findByEmail(userRequestDTO.email()).isPresent()){
            throw new RuntimeException("Email já cadastrado");
        }

        User newUser = new User();
        newUser.setName(userRequestDTO.name());
        newUser.setEmail(userRequestDTO.email());
        newUser.setPassword(userRequestDTO.password());
        newUser.setAvatar(userRequestDTO.avatar());

        return userRepository.save(newUser);
    }


}
