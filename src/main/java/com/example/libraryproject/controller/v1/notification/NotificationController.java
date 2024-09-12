package com.example.libraryproject.controller.v1.notification;

import com.example.libraryproject.model.dto.response.payload.NotificationResponse;
import com.example.libraryproject.model.dto.response.base.BaseResponse;
import com.example.libraryproject.repository.notification.NotificationRepository;
import com.example.libraryproject.service.notification.NotificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notification")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationController {
    final NotificationService notificationService;
    private final NotificationRepository notificationRepository;

    @PostMapping("/send-reminder-notification/{reminderId}")
    public BaseResponse<Void> sendReminderNotification(@PathVariable("reminderId") Long reminderId) {
        notificationService.sendReminderNotification(reminderId);
        return BaseResponse.success();
    }

    @PostMapping("/send-new-book-notification/{bookId}/{userId}")
    public BaseResponse<Void> sendNewBookNotification(@PathVariable("bookId") Long bookId, @PathVariable("userId") Long userId) {
        notificationService.sendNewBookNotification(bookId, userId);
        return BaseResponse.success();
    }

    @PostMapping("/send-event-notification/{eventId}/{userId}")
    public BaseResponse<Void> sendEventNotification(@PathVariable("eventId") Long eventId, @PathVariable("userId") Long userId) {
        notificationService.sendEventNotification(eventId, userId);
        return BaseResponse.success();
    }
    @GetMapping("/get-notifications-by-user/{userId}")
    public BaseResponse<List<NotificationResponse>> getNotificationsByUserId(@PathVariable("userId") Long userId) {
        return BaseResponse.success(notificationService.getNotificationsByUser(userId));
    }
    @DeleteMapping("/remove-notification/{notificationId}")
    public BaseResponse<Void> removeNotification(@PathVariable("notificationId") Long notificationId) {
        notificationService.removeNotification(notificationId);
        return BaseResponse.success();
    }
}
