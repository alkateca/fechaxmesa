package com.alkateca.posts.dto;

import com.alkateca.posts.enums.Tag;

public record PostResponseDTO(
        Long id,
        String title,
        String content,
        Tag tags,
        Long userId

) {
}
