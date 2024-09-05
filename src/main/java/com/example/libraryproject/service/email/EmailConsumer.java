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

    @RabbitListener(queues = "reminderQueue")
    public void receiveReminderMessage(Map<String, String> message) throws Exception {
        String email = message.get("email");
        String bookName = message.get("bookName");
        String day = message.get("day");
        emailService.sendReminderHtmlMail("Reminder", email, bookName,day);
    }
    @RabbitListener(queues = "overdueQueue")
    public void receiveOverdueReminderMessage(Map<String, String> message) throws Exception{
        String email = message.get("email");
        String bookName = message.get("bookName");
        String day = message.get("day");
        emailService.sendOverdueHtmlMail("Overdue", email, bookName,day);
    }


    @RabbitListener(queues = "bookQueue")
    public void receiveBookMessage(Map<String, String> message) throws Exception {
        String email = message.get("email");
        String bookName = message.get("bookName");
        String bookId = message.get("bookId");
        emailService.sendBookHtmlMail("New Book", email,bookId, bookName);
    }

    @RabbitListener(queues = "eventQueue")
    public void receiveEventMessage(Map<String, String> message) throws Exception {
        String email = message.get("email");
        String eventName = message.get("eventName");
        emailService.sendEventHtmlMail( "New Event", email,eventName);
    }


}

