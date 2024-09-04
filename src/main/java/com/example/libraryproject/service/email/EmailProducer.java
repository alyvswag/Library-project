package com.example.libraryproject.service.email;

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

    public void sendReminderNotification(String email,  String bookName) {
        Map<String, String> notificationMessage = new HashMap<>();
        notificationMessage.put("email", email);
        notificationMessage.put("bookName", bookName);
        rabbitTemplate.convertAndSend("emailTopicExchange", "reminder.key", notificationMessage);
    }
    public void sendBookNotification(String email,String bookId,  String bookName) {
        Map<String, String> notificationMessage = new HashMap<>();
        notificationMessage.put("email", email);
        notificationMessage.put("bookId", bookId);
        notificationMessage.put("bookName", bookName);
        rabbitTemplate.convertAndSend("emailTopicExchange", "book.key", notificationMessage);
    }
    public void sendEventNotification(String email,  String eventName) {
        Map<String, String> notificationMessage = new HashMap<>();
        notificationMessage.put("email", email);
        notificationMessage.put("eventName", eventName);
        rabbitTemplate.convertAndSend("emailTopicExchange", "event.key", notificationMessage);
    }


}

