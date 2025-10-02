package com.alkateca.mailing.dto; // Crie o pacote se não existir

// Usar um record é uma forma moderna e concisa
public record UserResponseDTO(
        Long id,
        String name,
        String email) {
}