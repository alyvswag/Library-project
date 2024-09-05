package com.example.libraryproject.service.reservation;

import com.example.libraryproject.model.dto.request.create.ReservationRequestCreate;
import com.example.libraryproject.model.dto.request.update.ReservationRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.ReservationResponseAdmin;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    void addReservation(ReservationRequestCreate request);

    void updateReservation(Long id, ReservationRequestUpdate request);

    void cancelReservation(Long id);

    List<ReservationResponseAdmin> getUserReservations(Long userId);

    List<ReservationResponseAdmin> getBookReservations(Long bookId);

    ReservationResponseAdmin getReservationDetails(Long id);

    Boolean checkAvailability(Long bookId, LocalDate startDate, LocalDate endDate);
}
