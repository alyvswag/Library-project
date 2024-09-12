package com.example.libraryproject.service.reminder;

import com.example.libraryproject.model.dto.response.payload.ReminderResponse;


import java.time.LocalDate;
import java.util.List;

public interface ReminderService {
    void addReminder(Long rentalId);

    void removeReminder(Long reminderId);

    List<ReminderResponse> getRemindersByUser(Long userId);

    List<ReminderResponse> getUpcomingReminders(LocalDate date);
}
