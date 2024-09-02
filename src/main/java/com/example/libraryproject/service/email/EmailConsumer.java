package com.example.libraryproject.service.email;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class EmailConsumer {

    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "emailQueue")
    public void receiveMessage(Map<String, String> message) throws InterruptedException {
        String email = message.get("email");
        String password = message.get("password");
        emailService.sendMail(email, password);
        //todo: thread vererek email 1 saniye arayla gondermek olar
    }
}

