package com.example.libraryproject.service.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class EmailService {
    final JavaMailSender mailSender;
    final ResourceLoader resourceLoader;

    public void sendMail(String email, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("support@domain.com");
        message.setTo(email);
        message.setText("Salam sizin admin passwordunuz: " + text);
        message.setSubject("Library Admin");
        mailSender.send(message);
    }

    public void sendReminderHtmlMail( String subject, String userEmail, String bookName, String day) {
        try {
            Resource resource = resourceLoader.getResource("classpath:templates/reminder.html");
            String htmlContent = new String(Files.readAllBytes(resource.getFile().toPath()));

            htmlContent = htmlContent.replace("{userEmail}", userEmail);
            htmlContent = htmlContent.replace("{bookName}", bookName);
            htmlContent = htmlContent.replace("{day}", day);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("support@domain.com");
            helper.setTo(userEmail);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
    }
    public void sendOverdueHtmlMail( String subject, String userEmail, String bookName, String day) {
        try {
            Resource resource = resourceLoader.getResource("classpath:templates/overdue.html");
            String htmlContent = new String(Files.readAllBytes(resource.getFile().toPath()));

            htmlContent = htmlContent.replace("{userEmail}", userEmail);
            htmlContent = htmlContent.replace("{bookName}", bookName);
            htmlContent = htmlContent.replace("{day}", day);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("support@domain.com");
            helper.setTo(userEmail);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendBookHtmlMail( String subject, String userEmail, String bookId, String bookName) {
        try {
            Resource resource = resourceLoader.getResource("classpath:templates/book.html");
            String htmlContent = new String(Files.readAllBytes(resource.getFile().toPath()));

            htmlContent = htmlContent.replace("{userEmail}", userEmail);
            htmlContent = htmlContent.replace("{bookName}", bookName);
            htmlContent = htmlContent.replace("{bookId}", bookId);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("support@domain.com");
            helper.setTo(userEmail);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendEventHtmlMail( String subject, String userEmail, String eventName) {
        try {
            Resource resource = resourceLoader.getResource("classpath:templates/event.html");
            String htmlContent = new String(Files.readAllBytes(resource.getFile().toPath()));

            htmlContent = htmlContent.replace("{userEmail}", userEmail);
            htmlContent = htmlContent.replace("{eventName}", eventName);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("support@domain.com");
            helper.setTo(userEmail);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
    }
//todo: Oxunma təsdiqi üçün başlıq əlavə edin
//        message.setHeader("Disposition-Notification-To", "support@domain.com");
}
