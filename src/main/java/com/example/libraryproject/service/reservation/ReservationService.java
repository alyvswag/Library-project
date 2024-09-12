package com.example.libraryproject.service.reservation;

import com.example.libraryproject.model.dto.request.create.ReservationRequestCreate;
import com.example.libraryproject.model.dto.request.update.ReservationRequestUpdate;
import com.example.libraryproject.model.dto.response.payload.ReservationResponse;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    void addReservation(ReservationRequestCreate request);

    void updateReservation(Long id, ReservationRequestUpdate request);

    void cancelReservation(Long id);

    List<ReservationResponse> getUserReservations(Long userId);

    List<ReservationResponse> getBookReservations(Long bookId);

    ReservationResponse getReservationDetails(Long id);

    Boolean checkAvailability(Long bookId, LocalDate startDate, LocalDate endDate);
}
