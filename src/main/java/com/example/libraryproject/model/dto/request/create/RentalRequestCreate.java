package com.example.libraryproject.model.dto.request.create;

import com.example.libraryproject.model.enums.rental.RentalStatus;
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
public class RentalRequestCreate {
    LocalDate rentalStartDate;
    LocalDate rentalEndDate;
}
