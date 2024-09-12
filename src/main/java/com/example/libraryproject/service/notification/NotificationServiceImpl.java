package com.example.libraryproject.service.notification;

import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.mapper.notification.NotificationMapper;
import com.example.libraryproject.model.dao.*;
import com.example.libraryproject.model.dto.request.create.NotificationRequestCreate;
import com.example.libraryproject.model.dto.response.payload.NotificationResponse;
import com.example.libraryproject.model.enums.notification.DataType;
import com.example.libraryproject.repository.book.BookRepository;
import com.example.libraryproject.repository.event.EventRepository;
import com.example.libraryproject.repository.notification.NotificationRepository;
import com.example.libraryproject.repository.reminder.ReminderRepository;
import com.example.libraryproject.repository.user.UserRepository;
import com.example.libraryproject.service.email.EmailProducer;
import com.example.libraryproject.service.email.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.example.libraryproject.model.enums.notification.DataType.*;
import static com.example.libraryproject.model.enums.notification.Message.*;
import static com.example.libraryproject.model.enums.notification.NotificationStatus.DELETED;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationServiceImpl implements NotificationService {
    final NotificationMapper notificationMapper;
    final NotificationRepository notificationRepository;
    final ReminderRepository reminderRepository;
    final BookRepository bookRepository;
    final UserRepository userRepository;
    final EventRepository eventRepository;
    final EmailProducer emailProducer;
    private final EmailService emailService;

    @Override
    public void sendReminderNotification(Long reminderId) {
        Reminder reminderEntity = findReminderById(reminderId);
        Long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), (reminderEntity.getReminderDate().plusDays(1)));
        emailProducer.sendReminderNotification(reminderEntity.getUser().getEmail(), reminderEntity.getBook().getBookName(), String.valueOf(daysBetween));
        saveNotification(reminderEntity.getUser().getId()
                , REMINDER,
                String.format(REMINDER_MESSAGE.message(), reminderEntity.getBook().getBookName(), daysBetween));
    }

    @Override
    public void sendNewBookNotification(Long bookId, Long userId) {
        Book bookEntity = findBookById(bookId);
        User userEntity = findUserById(userId);
        emailProducer.sendBookNotification(userEntity.getEmail(), String.valueOf(bookId), bookEntity.getBookName());
        saveNotification(userId, BOOK, String.format(NEW_BOOK_MESSAGE.message(), bookEntity.getBookName()));
    }

    @Override
    public void sendEventNotification(Long eventId, Long userId) {
        Event eventEntity = findEventById(eventId);
        User userEntity = findUserById(userId);
        emailProducer.sendEventNotification(userEntity.getEmail(), eventEntity.getEventName());
        saveNotification(userId, EVENT, String.format(EVENT_MESSAGE.message(), eventEntity.getEventName()));
    }

    @Override
    public List<NotificationResponse> getNotificationsByUser(Long userId) {
        return notificationMapper.toDto(notificationRepository.findNotificationsByUserId(userId));
    }

    @Override
    public void removeNotification(Long notificationId) {
        Notification notificationEntity = findNotificationById(notificationId);
        notificationEntity.setNotificationStatus(DELETED);
        notificationRepository.save(notificationEntity);
    }


    //private
    private User findUserById(Long id) {
        return userRepository.findUserById(id)
                .orElseThrow(
                        () -> BaseException.notFound(User.class.getSimpleName(), "user", String.valueOf(id)));
    }

    private Book findBookById(Long id) {
        return bookRepository.findBookById(id)
                .orElseThrow(
                        () -> BaseException.notFound(Book.class.getSimpleName(), "book", String.valueOf(id)));
    }

    private Event findEventById(Long id) {
        return eventRepository.findEventById(id)
                .orElseThrow(() -> BaseException.notFound(Event.class.getSimpleName(), "event", String.valueOf(id)));
    }

    private Reminder findReminderById(Long id) {
        return reminderRepository.findReminderById(id)
                .orElseThrow(() -> BaseException.notFound(Reminder.class.getSimpleName(), "reminder", String.valueOf(id)));
    }

    private Notification findNotificationById(Long id) {
        return notificationRepository.findNotificationById(id).orElseThrow(
                () -> BaseException.notFound(Notification.class.getSimpleName(), "notification", String.valueOf(id))
        );
    }

    private void saveNotification(Long userId, DataType dataType, String message) {
        notificationRepository.save(notificationMapper.toEntity(NotificationRequestCreate.builder()
                .userId(userId)
                .dataType(dataType)
                .message(message)
                .build()
        ));
    }


}
