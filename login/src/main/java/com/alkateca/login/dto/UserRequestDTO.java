package com.alkateca.login.dto;

import com.alkateca.login.enums.Avatar;


public record UserRequestDTO(
        String name,
        String email,
        String password,
        Avatar avatar
) {

}
