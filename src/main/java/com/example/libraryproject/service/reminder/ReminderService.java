package com.example.libraryproject.service.reminder;

import com.example.libraryproject.model.dto.request.create.ReminderRequestCreate;
import com.example.libraryproject.model.dto.response.admin.ReminderResponseAdmin;
import com.example.libraryproject.model.dto.response.user.ReminderResponseUser;

import java.time.LocalDate;
import java.util.List;

public interface ReminderService {
    void addReminder(Long rentalId);

    void removeReminder(Long reminderId);

    List<ReminderResponseUser> getRemindersByUser(Long userId);

    List<ReminderResponseAdmin> getUpcomingReminders(LocalDate date);
}
