package com.example.libraryproject.repository.reminder;

import com.example.libraryproject.model.dao.Event;
import com.example.libraryproject.model.dao.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Integer> {
    @Query("SELECT r FROM Reminder r WHERE r.id = :id AND r.isActive = true")
    Optional<Reminder> findReminderById(@Param("id") Long id);

    @Query("Select r from Reminder r where r.user.id = :userId ")
    List<Reminder> findAllReminderByUserId(@Param("userId")Long userId);

    @Query("select r from Reminder r where r.reminderDate>= :date and r.isActive = true ")
    List<Reminder> findUpComingReminders(@Param("date") LocalDate date);
}
