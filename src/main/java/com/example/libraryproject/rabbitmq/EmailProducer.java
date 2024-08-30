package com.example.libraryproject.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailProducer {
    private final RabbitTemplate rabbitTemplate;

    public EmailProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendEmailMessage(String email, String password) {
        Map<String, String> message = new HashMap<>();
        message.put("email", email);
        message.put("password", password);
        rabbitTemplate.convertAndSend("emailTopicExchange", "email.key", message);
    }

}

