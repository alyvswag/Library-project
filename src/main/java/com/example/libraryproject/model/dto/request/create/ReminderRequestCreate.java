package com.example.libraryproject.model.dto.request.create;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReminderRequestCreate {
    Long bookId;
    Long userId;
    LocalDate reminderDate;
}
