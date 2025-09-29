package com.alkateca.mailing.service;

import com.alkateca.mailing.client.UserApiClient;
import com.alkateca.mailing.dto.UserResponseDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final UserApiClient userApiClient;
    private final EmailTemplateService emailTemplateService;

    public EmailService(JavaMailSender mailSender, UserApiClient userApiClient, EmailTemplateService emailTemplateService) {
        this.mailSender = mailSender;
        this.userApiClient = userApiClient;
        this.emailTemplateService = emailTemplateService;
    }

    public void buildAdventureRequestEmail(Long masterId, Long requesterId, String subject) throws MessagingException {
        UserResponseDTO master = userApiClient.findUserById(masterId);

        UserResponseDTO requester = userApiClient.findUserById(requesterId);

        String htmlBody = emailTemplateService.buildAdventureRequestEmail(requester.name(), requester.email());

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");


        helper.setTo(master.email());

        helper.setSubject(subject);
        helper.setText(htmlBody, true);

        mailSender.send(message);
    }
}