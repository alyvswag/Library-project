package com.example.libraryproject.service.notification;

import com.example.libraryproject.model.dto.response.payload.NotificationResponse;

import java.util.List;

public interface NotificationService {
    void sendReminderNotification(Long reminderId);
    void sendNewBookNotification(Long bookId,Long userId);
    void sendEventNotification(Long eventId,Long userId);
    List<NotificationResponse> getNotificationsByUser(Long userId);
    void removeNotification(Long notificationId);
}
