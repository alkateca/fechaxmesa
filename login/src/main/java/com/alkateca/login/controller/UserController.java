package com.alkateca.login.controller;

import com.alkateca.login.dto.LoginRequestDTO;
import com.alkateca.login.dto.UserRequestDTO;
import com.alkateca.login.dto.UserResponseDTO;
import com.alkateca.login.dto.UserUpdateRequestDTO;
import com.alkateca.login.model.User;
import com.alkateca.login.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        User newUser = userService.createUser(userRequestDTO);
        UserResponseDTO responseDTO = new UserResponseDTO(
                newUser.getId(),
                newUser.getName(),
                newUser.getEmail(),
                newUser.getAvatar()
        );

        return  ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        User updatedUser = userService.updateUser(id, userUpdateRequestDTO);

        UserResponseDTO responseDTO = new UserResponseDTO(
                updatedUser.getId(),
                updatedUser.getName(),
                updatedUser.getEmail(),
                updatedUser.getAvatar()
        );

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }


    //Criar conexão com serviço de email

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        UserResponseDTO responseDTO = new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAvatar()
        );
        return ResponseEntity.ok(responseDTO);
    }
}
