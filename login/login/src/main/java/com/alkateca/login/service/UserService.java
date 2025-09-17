package com.alkateca.login.service;

import com.alkateca.login.dto.UserRequestDTO;
import com.alkateca.login.model.User;
import com.alkateca.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(UserRequestDTO userRequestDTO) {
        if(userRepository.findByEmail(userRequestDTO.email()).isPresent()){
            throw new RuntimeException("Email já cadastrado");
        }

        User newUser = new User();
        newUser.setName(userRequestDTO.name());
        newUser.setPassword(passwordEncoder.encode(userRequestDTO.password()));
        newUser.setEmail(userRequestDTO.email());
        newUser.setAvatar(userRequestDTO.avatar());

        return userRepository.save(newUser);
    }

}
