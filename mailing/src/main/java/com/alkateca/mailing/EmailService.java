package com.alkateca.mailing;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("usuario1@policorp");
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void sendHTMLEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");


        helper.setFrom("usuario1@policorp");
        helper.setTo(to);
        helper.setSubject(subject);

        String htmlContent = "<h1>Olha dani, está funcionando!</h1>" +
                "<p>Isso é um <strong>teste!</strong></p>";

        helper.setText(htmlContent, true);

        mailSender.send(message);
    }

}
