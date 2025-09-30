package com.alkateca.login.service;

import com.alkateca.login.dto.UserRequestDTO;
import com.alkateca.login.model.User;
import com.alkateca.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


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

    public User updateUser(Long id, @RequestBody UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if(userRequestDTO.email() != null){
            user.setEmail(userRequestDTO.email());
        }
        if(userRequestDTO.avatar() != null){
            user.setAvatar(userRequestDTO.avatar());
        }
        if(userRequestDTO.name() != null){
            user.setName(userRequestDTO.name());
        }
        if(userRequestDTO.password() != null){
            user.setPassword(userRequestDTO.password());
        }

        return userRepository.save(user);
    }

    //Criar conexão com o serviço de email
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
