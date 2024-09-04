package com.example.libraryproject.model.dto.response.admin;

import com.example.libraryproject.model.dto.response.user.BookResponseUser;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReminderResponseAdmin {
    UserResponseAdmin user;
    BookResponseAdmin book;
    LocalDate reminderDate;
    Boolean isActive;
    Timestamp createdAt;
    Timestamp updatedAt;
}
