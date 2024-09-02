package com.example.libraryproject.service.rental;

import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.mapper.rental.RentalMapper;
import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dao.Rental;
import com.example.libraryproject.model.dao.Reservation;
import com.example.libraryproject.model.dto.request.create.RentalRequestCreate;
import com.example.libraryproject.model.enums.reservation.RezervStatus;
import com.example.libraryproject.repository.book.BookRepository;
import com.example.libraryproject.repository.rental.RentalRepository;
import com.example.libraryproject.repository.reservation.ReservationRepository;
import com.example.libraryproject.service.reservation.ReservationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static com.example.libraryproject.model.enums.reservation.RezervStatus.CONFIRMED;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RentalService {
    final RentalRepository rentalRepository;
    final RentalMapper rentalMapper;
    final ReservationRepository reservationRepository;
    final BookRepository bookRepository;

    public void addRental(Long reservationId, RentalRequestCreate rentalRequestCreate) {
        Reservation reservationEntity = reservationRepository.findReservationById(reservationId).orElseThrow(
                () -> BaseException.notFound(Reservation.class.getSimpleName(), "reservation", String.valueOf(reservationId))
        );
        reservationEntity.setStatus(CONFIRMED);

        Rental rentalEntity = rentalMapper.toEntityRental(reservationEntity);
        rentalMapper.dtoModelMappingDao(rentalRequestCreate, rentalEntity);

        Book bookEntity = rentalEntity.getBook();
        bookEntity.getQuantityBook().setReservedQuantity(
                bookEntity.getQuantityBook().getReservedQuantity() - 1);
        bookEntity.getQuantityBook().setRentalQuantity(
                bookEntity.getQuantityBook().getRentalQuantity() + 1);

        reservationRepository.save(reservationEntity);
        bookRepository.save(bookEntity);
        rentalRepository.save(rentalEntity);
    }
}
