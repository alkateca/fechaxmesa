package com.alkateca.login.service;

import com.alkateca.login.dto.UserRequestDTO;
import com.alkateca.login.dto.UserUpdateRequestDTO;
import com.alkateca.login.model.User;
import com.alkateca.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


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

    public User updateUser(Long id, @RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if(userUpdateRequestDTO.email() != null){
            user.setEmail(userUpdateRequestDTO.email());
        }
        if(userUpdateRequestDTO.avatar() != null){
            user.setAvatar(userUpdateRequestDTO.avatar());
        }
        if(userUpdateRequestDTO.name() != null){
            user.setName(userUpdateRequestDTO.name());
        }
        if(userUpdateRequestDTO.password() != null){
            user.setPassword(passwordEncoder.encode(userUpdateRequestDTO.password()));
        }

        return userRepository.save(user);
    }

    //Criar conexão com o serviço de email
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

}
