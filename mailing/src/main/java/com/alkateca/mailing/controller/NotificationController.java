package com.alkateca.mailing.controller; // Crie este pacote se não existir

import com.alkateca.mailing.dto.NotificationRequestDTO;
import com.alkateca.mailing.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final EmailService emailService;

    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping()
    public ResponseEntity<String> sendAdventureRequest(@RequestBody NotificationRequestDTO request) {
        try {
            emailService.buildAdventureRequestEmail(
                    request.masterId(),
                    request.requesterId(),
                    request.subject()
            );
            return ResponseEntity.ok("Notificação enviada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Falha ao enviar notificação: " + e.getMessage());
        }
    }
}