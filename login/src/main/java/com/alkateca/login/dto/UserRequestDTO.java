package com.alkateca.login.dto;

import com.alkateca.login.enums.Avatar;
import jakarta.validation.constraints.NotNull;


public record UserRequestDTO(
        @NotNull(message="O nome é obrigatório")
        String name,
        @NotNull(message="O e-mail é obrigatório")
        String email,
        @NotNull(message="A senha é obrigatória")
        String password,
        @NotNull(message="O Avatar é obrigatório")
        Avatar avatar
) {

}
