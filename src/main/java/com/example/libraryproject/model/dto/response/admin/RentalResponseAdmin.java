package com.example.libraryproject.model.dto.response.admin;

import com.example.libraryproject.model.enums.rental.RentalStatus;
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
public class RentalResponseAdmin {
    Long id;
    UserResponseAdmin user;
    BookResponseAdmin book;
    LocalDate rentalStartDate;
    LocalDate rentalEndDate;
    RentalStatus rentalStatus;
    LocalDate returnDate;
    Timestamp createdAt;
    Timestamp updatedAt;
}
