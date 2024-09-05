package com.example.libraryproject.service.management;

import com.example.libraryproject.model.dto.response.admin.RentalResponseAdmin;
import com.example.libraryproject.model.enums.rental.RentalStatus;

import java.time.LocalDate;
import java.util.List;

public interface ManagementService {
    List<RentalResponseAdmin> checkOverdueBooks();

    void sendAllOverdueBooksNotices();

    void sendOverdueNotices(Long rentalId);

    void updateBookStatus(Long rentalId, RentalStatus rentalStatus);

    void logReturnEvent(Long rentalId, LocalDate returnDate);
}
