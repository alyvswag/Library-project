package com.example.libraryproject.model.dto.response.admin;

import com.example.libraryproject.model.enums.reservation.RezervStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ReservationResponseAdmin {
    Long id;
    BookResponseAdmin book;
    UserResponseAdmin user;
    LocalDate startDate;
    LocalDate endDate;
    RezervStatus status;
    Timestamp createdAt;
    Timestamp  updatedAt;
}
