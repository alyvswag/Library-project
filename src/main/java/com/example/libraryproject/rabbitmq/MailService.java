package com.example.libraryproject.rabbitmq;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class MailService {
        final  JavaMailSender mailSender;
        public void sendMail(String email,String text) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("support@domain.com");
            message.setTo(email);
            message.setText("Salam sizin admin passwordunuz: " + text);
            message.setSubject("Library Admin");
            mailSender.send(message);
        }
}
