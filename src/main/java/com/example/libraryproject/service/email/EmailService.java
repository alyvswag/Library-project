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

import static com.example.libraryproject.constant.HtmlPathConstants.*;

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
        message.setText("Salam sizin passwordunuz: " + text + "\n" +
                "Zehmet olmasa heckesle paylasmayin.");
        message.setSubject("Masazir Library");
        mailSender.send(message);
    }

    public void sendReminderHtmlMail(String subject, String userEmail, String bookName, String day) throws IOException, MessagingException {
        Resource resource = resourceLoader.getResource(reminderTemplatePath);
        String htmlContent = new String(Files.readAllBytes(resource.getFile().toPath()));

        htmlContent = htmlContent.replace("{userEmail}", userEmail);
        htmlContent = htmlContent.replace("{bookName}", bookName);
        htmlContent = htmlContent.replace("{day}", day);

        MimeMessage message = createMimeMessage(htmlContent, userEmail, subject);
        mailSender.send(message);
    }

    public void sendOverdueHtmlMail(String subject, String userEmail, String bookName, String day) throws MessagingException, IOException {
        Resource resource = resourceLoader.getResource(overdueTemplatePath);
        String htmlContent = new String(Files.readAllBytes(resource.getFile().toPath()));

        htmlContent = htmlContent.replace("{userEmail}", userEmail);
        htmlContent = htmlContent.replace("{bookName}", bookName);
        htmlContent = htmlContent.replace("{day}", day);

        MimeMessage message = createMimeMessage(htmlContent, userEmail, subject);

        mailSender.send(message);
    }

    public void sendBookHtmlMail(String subject, String userEmail, String bookId, String bookName) throws IOException, MessagingException {
        Resource resource = resourceLoader.getResource(bookTemplatePath);
        String htmlContent = new String(Files.readAllBytes(resource.getFile().toPath()));

        htmlContent = htmlContent.replace("{userEmail}", userEmail);
        htmlContent = htmlContent.replace("{bookName}", bookName);
        htmlContent = htmlContent.replace("{bookId}", bookId);

        MimeMessage message = createMimeMessage(htmlContent, userEmail, subject);

        mailSender.send(message);
    }

    public void sendEventHtmlMail( String subject, String userEmail, String eventName) throws IOException, MessagingException {
        Resource resource = resourceLoader.getResource(eventTemplatePath);
        String htmlContent = new String(Files.readAllBytes(resource.getFile().toPath()));

        htmlContent = htmlContent.replace("{userEmail}", userEmail);
        htmlContent = htmlContent.replace("{eventName}", eventName);

        MimeMessage message = createMimeMessage(htmlContent, userEmail, subject);
        mailSender.send(message);
    }

    private MimeMessage createMimeMessage(String htmlContent, String userEmail, String subject) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("support@domain.com");
        helper.setTo(userEmail);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        return message;
    }


//todo: Oxunma təsdiqi üçün başlıq əlavə edin
//        message.setHeader("Disposition-Notification-To", "support@domain.com");
}
