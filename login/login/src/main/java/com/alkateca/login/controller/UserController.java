package com.alkateca.login.controller;

import com.alkateca.login.dto.UserRequestDTO;
import com.alkateca.login.dto.UserResponseDTO;
import com.alkateca.login.model.User;
import com.alkateca.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO userRequestDTO) {
        User newUser = userService.createUser(userRequestDTO);
        UserResponseDTO responseDTO = new UserResponseDTO(
                newUser.getId(),
                newUser.getName(),
                newUser.getEmail(),
                newUser.getAvatar()
        );

        return  ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }


}
