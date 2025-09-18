package com.alkateca.login.dto;

import com.alkateca.login.enums.Avatar;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(
        @NotBlank(message = "O nome não pode estar em branco")
        String name,

        @NotBlank(message = "O email não pode estar em branco")
        String email,

        @NotBlank(message = "A senha não pode estar em branco")
        String password,

        @NotNull(message = "O avatar não pode ser nulo")
        Avatar avatar
) {

}
