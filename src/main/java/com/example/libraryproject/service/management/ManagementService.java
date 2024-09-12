package com.example.libraryproject.service.management;

import com.example.libraryproject.model.dto.response.payload.RentalResponse;
import com.example.libraryproject.model.enums.rental.RentalStatus;

import java.time.LocalDate;
import java.util.List;

public interface ManagementService {
    List<RentalResponse> checkOverdueBooks();

    void sendAllOverdueBooksNotices();

    void sendOverdueNotices(Long rentalId);

    void updateBookStatus(Long rentalId, RentalStatus rentalStatus);

    void logReturnEvent(Long rentalId, LocalDate returnDate);
}
