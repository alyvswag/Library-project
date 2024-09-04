package com.example.libraryproject.model.dao;

import com.example.libraryproject.model.dao.entity.Base;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reminders")
public class Reminder extends Base {
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    Book book;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    User user;
    @Column(name = "reminder_date")
    LocalDate reminderDate;
    @Column(name = "is_active")
    Boolean isActive;
}
