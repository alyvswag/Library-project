package com.example.libraryproject.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class EmailConsumer {

    private final MailService mailService;

    public EmailConsumer(MailService mailService) {
        this.mailService = mailService;
    }

    @RabbitListener(queues = "emailQueue")
    public void receiveMessage(Map<String, String> message) throws InterruptedException {
        String email = message.get("email");
        String password = message.get("password");
        mailService.sendMail(email, password);
        //todo: thread vererek email 1 saniye arayla gondermek olar
    }
}

