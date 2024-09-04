package com.example.libraryproject.controller.v1.reminder;

import com.example.libraryproject.model.dto.request.create.ReminderRequestCreate;
import com.example.libraryproject.model.dto.response.admin.ReminderResponseAdmin;
import com.example.libraryproject.model.dto.response.base.BaseResponse;
import com.example.libraryproject.model.dto.response.user.ReminderResponseUser;
import com.example.libraryproject.service.reminder.ReminderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reminder")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ReminderController {
    final ReminderService reminderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add-reminder/{rentalId}")
    public BaseResponse<Void> addReminder(@PathVariable("rentalId") Long rentalId) {
        reminderService.addReminder(rentalId);
        return BaseResponse.created();
    }
    @DeleteMapping("/delete-reminder")
    public BaseResponse<Void> removeReminder(@RequestParam("id") long id) {
        reminderService.removeReminder(id);
        return BaseResponse.success();
    }
    @GetMapping("/get-reminders-by-user/{userId}")
    public BaseResponse<List<ReminderResponseUser>> getRemindersByUserId(@PathVariable("userId") Long userId) {
        return BaseResponse.success(reminderService.getRemindersByUser(userId));
    }
    @GetMapping("/get-upcoming-reminders/{date}")
    public BaseResponse<List<ReminderResponseAdmin>> getUpcomingReminders(@PathVariable("date")LocalDate date){
        return BaseResponse.success(reminderService.getUpcomingReminders(date));
    }
}
