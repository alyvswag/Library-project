package com.example.libraryproject.service.reminder;

import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.mapper.reminder.ReminderMapper;
import com.example.libraryproject.model.dao.entity.Reminder;
import com.example.libraryproject.model.dao.entity.Rental;
import com.example.libraryproject.model.dto.request.create.ReminderRequestCreate;
import com.example.libraryproject.model.dto.response.payload.ReminderResponse;
import com.example.libraryproject.repository.reminder.ReminderRepository;
import com.example.libraryproject.repository.rental.RentalRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
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
    public List<ReminderResponse> getRemindersByUser(Long userId) {
        return reminderMapper.toResponse(reminderRepository.findAllReminderByUserId(userId));
    }

    @Override
    public List<ReminderResponse> getUpcomingReminders(LocalDate date) {
        return reminderMapper.toResponse(reminderRepository.findUpComingReminders(date));
    }

}
