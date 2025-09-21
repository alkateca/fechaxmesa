package com.alkateca.mailing;

import jakarta.mail.MessagingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MailingApplication {

    private EmailService emailService;

    public MailingApplication(EmailService emailService) {
        this.emailService = emailService;
    }

    public static void main(String[] args) throws MessagingException {
        ApplicationContext context = SpringApplication.run(MailingApplication.class, args);
        MailingApplication app = context.getBean(MailingApplication.class);

        app.Run();
    }

    private void Run() throws MessagingException {
        emailService.sendHTMLEmail("usuario2@policorp","email teste","echo echo echo");

    }


}
