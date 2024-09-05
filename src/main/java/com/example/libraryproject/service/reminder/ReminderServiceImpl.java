package com.example.libraryproject.service.reminder;

import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.mapper.reminder.ReminderMapper;
import com.example.libraryproject.model.dao.Reminder;
import com.example.libraryproject.model.dao.Rental;
import com.example.libraryproject.model.dto.request.create.ReminderRequestCreate;
import com.example.libraryproject.model.dto.response.admin.ReminderResponseAdmin;
import com.example.libraryproject.model.dto.response.user.ReminderResponseUser;
import com.example.libraryproject.repository.reminder.ReminderRepository;
import com.example.libraryproject.repository.rental.RentalRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class ReminderServiceImpl implements ReminderService {

    final ReminderRepository reminderRepository;
    final ReminderMapper reminderMapper;
    final RentalRepository rentalRepository;

    @Override
    public void addReminder(Long rentalId) {
        Rental rentalEntity = rentalRepository.findRentalByIdIsActive(rentalId).orElseThrow(
                () -> BaseException.notFound(Rental.class.getSimpleName(), "rental", String.valueOf(rentalId))
        );
        reminderRepository.save(reminderMapper.toEntity(
                ReminderRequestCreate.builder()
                        .userId(rentalEntity.getUser().getId())
                        .bookId(rentalEntity.getBook().getId())
                        .reminderDate(rentalEntity.getRentalEndDate().minusDays(1))
                        .build()
        ));
    }

    @Override
    public void removeReminder(Long reminderId) {
        Reminder reminderEntity = reminderRepository.findReminderById(reminderId)
                .orElseThrow(() -> BaseException.notFound(Reminder.class.getSimpleName(), "reminder", String.valueOf(reminderId)));
        reminderEntity.setIsActive(false);
        reminderRepository.save(reminderEntity);
    }

    @Override
    public List<ReminderResponseUser> getRemindersByUser(Long userId) {
        return reminderMapper.toResponseUser(reminderRepository.findAllReminderByUserId(userId));
    }

    @Override
    public List<ReminderResponseAdmin> getUpcomingReminders(LocalDate date) {
        return reminderMapper.toResponseAdmin(reminderRepository.findUpComingReminders(date));
    }

}
