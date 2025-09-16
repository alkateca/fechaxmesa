package com.alkateca.login.dto;

import com.alkateca.login.enums.Avatar;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        Avatar avatar
) {
}
