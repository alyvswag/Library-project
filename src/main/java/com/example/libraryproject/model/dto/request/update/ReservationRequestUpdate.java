package com.example.libraryproject.model.dto.request.update;

import com.example.libraryproject.model.enums.reservation.RezervStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestUpdate {
    LocalDate startDate;
    LocalDate endDate;
}
