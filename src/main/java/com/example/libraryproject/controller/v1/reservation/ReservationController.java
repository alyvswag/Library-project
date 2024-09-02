package com.example.libraryproject.controller.v1.reservation;

import com.example.libraryproject.model.dao.Reservation;
import com.example.libraryproject.model.dto.request.create.ReservationRequestCreate;
import com.example.libraryproject.model.dto.request.update.ReservationRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.ReservationResponseAdmin;
import com.example.libraryproject.model.dto.response.base.BaseResponse;
import com.example.libraryproject.service.reservation.ReservationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservation")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ReservationController {

    final ReservationService reservationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add-reservation")
    public BaseResponse<Void> addReservation(@RequestBody ReservationRequestCreate reservation) {
        reservationService.addReservation(reservation);
        return BaseResponse.success();
    }
    @PutMapping("/update-reservation/{reservationId}")
    public BaseResponse<Void> updateReservation(@PathVariable Long reservationId, @RequestBody ReservationRequestUpdate reservation) {
        reservationService.updateReservation(reservationId, reservation);
        return BaseResponse.success();
    }
    @DeleteMapping("/cancel-reservation/{reservationId}")
    public BaseResponse<Void> cancelReservation(@PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return BaseResponse.success();
    }
    @GetMapping("/get-user-reservations/{userId}")
    public BaseResponse<List<ReservationResponseAdmin>> getUserReservations(@PathVariable Long userId) {
        return BaseResponse.success(reservationService.getUserReservations(userId));
    }
    @GetMapping("/get-book-reservations/{bookId}")
    public BaseResponse<List<ReservationResponseAdmin>> getBookReservations(@PathVariable Long bookId) {
        return BaseResponse.success(reservationService.getBookReservations(bookId));
    }
    @GetMapping("/get-reservation-details/{reservationId}")
    public BaseResponse<ReservationResponseAdmin> getReservationDetails(@PathVariable Long reservationId) {
        return BaseResponse.success(reservationService.getReservationDetails(reservationId));
    }

}
