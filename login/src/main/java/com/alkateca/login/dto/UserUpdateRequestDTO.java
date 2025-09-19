package com.alkateca.login.dto;

import com.alkateca.login.enums.Avatar;

public record UserUpdateRequestDTO(
        String  name,
        String password,
        String email,
        Avatar avatar
) {
}
