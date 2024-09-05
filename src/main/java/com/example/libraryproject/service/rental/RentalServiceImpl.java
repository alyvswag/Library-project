package com.example.libraryproject.service.rental;

import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.mapper.rental.RentalMapper;
import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dao.Rental;
import com.example.libraryproject.model.dao.Reservation;
import com.example.libraryproject.model.dto.request.create.RentalRequestCreate;
import com.example.libraryproject.model.dto.request.update.RentalRequestUpdate;
import com.example.libraryproject.repository.book.BookRepository;
import com.example.libraryproject.repository.rental.RentalRepository;
import com.example.libraryproject.repository.reservation.ReservationRepository;
import com.example.libraryproject.service.book.BookManagementService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static com.example.libraryproject.model.enums.rental.RentalStatus.*;
import static com.example.libraryproject.model.enums.reservation.RezervStatus.CONFIRMED;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RentalServiceImpl implements RentalService {
    final RentalRepository rentalRepository;
    final RentalMapper rentalMapper;
    final ReservationRepository reservationRepository;
    final BookRepository bookRepository;
    final BookManagementService bookManagementService;

    @Override
    public synchronized void addRental(Long reservationId, RentalRequestCreate rentalRequestCreate) {
        Reservation reservationEntity = reservationRepository.findReservationById(reservationId).orElseThrow(
                () -> BaseException.notFound(Reservation.class.getSimpleName(), "reservation", String.valueOf(reservationId))
        );
        reservationEntity.setStatus(CONFIRMED);
        reservationRepository.save(reservationEntity);

        Rental rentalEntity = rentalMapper.toEntityRental(reservationEntity);
        rentalMapper.dtoModelMappingDao(rentalRequestCreate, rentalEntity);
        rentalRepository.save(rentalEntity);

        Book bookEntity = rentalEntity.getBook();
        bookManagementService.reservationQuantity(bookEntity, -1);
        bookManagementService.rentalQuantity(bookEntity, 1);
    }

    @Override
    public void updateRental(Long rentalId, RentalRequestUpdate rentalRequestUpdate) {
        Rental rentalEntity = findRentalById(rentalId);

        rentalMapper.updateRentalFromDto(rentalRequestUpdate, rentalEntity);
        rentalRepository.save(rentalEntity);
    }

    @Override
    public synchronized void confirmedRental(Long rentalId) {
        Rental rentalEntity = findRentalById(rentalId);

        Book bookEntity = rentalEntity.getBook();
        rentalEntity.setRentalStatus(RETURNED);
        bookManagementService.rentalQuantity(bookEntity, -1);

        rentalRepository.save(rentalEntity);
    }

    //private
    private Rental findRentalById(Long id) {
        return rentalRepository.findRentalById(id).orElseThrow(
                () -> BaseException.notFound(Rental.class.getSimpleName(), "rental", String.valueOf(id))
        );
    }

}
