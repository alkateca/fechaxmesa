package com.alkateca.mailing.dto; // Use o pacote de DTOs que você já tem

public record NotificationRequestDTO(Long masterId, Long requesterId, String subject) {
}