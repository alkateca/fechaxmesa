package com.alkateca.posts.dto;

import com.alkateca.posts.enums.Tag;
import jakarta.validation.constraints.NotBlank;

public record PostRequestDTO(
        @NotBlank(message = "O titulo não pode ser em branco")
        String title,
        @NotBlank(message = "O conteúdo não pode ser em branco")
        String content,
        @NotBlank(message = "A tag não pode ser em branco")
        Tag tags,
        Long userId
) {
}
