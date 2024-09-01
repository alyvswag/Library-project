package com.example.libraryproject.service.reservation;

import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.mapper.reservation.ReservationMapper;
import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dao.RatingAndReview;
import com.example.libraryproject.model.dao.Reservation;
import com.example.libraryproject.model.dto.request.create.ReservationRequestCreate;
import com.example.libraryproject.model.dto.request.update.ReservationRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.ReservationResponseAdmin;
import com.example.libraryproject.model.enums.reservation.RezervStatus;
import com.example.libraryproject.repository.book.BookRepository;
import com.example.libraryproject.repository.reservation.ReservationRepository;
import com.example.libraryproject.service.book.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.libraryproject.model.enums.reservation.RezervStatus.CANCELLED;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ReservationService {

    final ReservationRepository reservationRepository;
    final ReservationMapper reservationMapper;
    final BookRepository bookRepository;

    public void addReservation(ReservationRequestCreate request) {
        Reservation reservationEntity = reservationMapper.toEntity(request);
        Book bookEntity = bookRepository.findBookById(reservationEntity.getBook().getId()).orElseThrow(
                () -> BaseException.notFound(Book.class.getSimpleName(), "book", String.valueOf(reservationEntity.getBook().getId()))
        );

        Integer quantity  = bookEntity.getQuantityBook().getQuantity();
        bookEntity.getQuantityBook().setQuantity(quantity - 1);

        bookRepository.save(bookEntity);
        reservationRepository.save(reservationEntity);
    }

    public void updateReservation(Long id, ReservationRequestUpdate request) {
        Reservation entity = reservationRepository.findReservationById(id).orElseThrow(
                () -> BaseException.notFound(Reservation.class.getSimpleName(), "reservation", String.valueOf(id))
        );
        reservationMapper.updateReservationFromDto(request, entity);
        reservationRepository.save(entity);
    }

    public void cancelReservation(Long id) {
        Reservation reservationEntity = reservationRepository.findReservationById(id).orElseThrow(
                () -> BaseException.notFound(Reservation.class.getSimpleName(), "reservation", String.valueOf(id))
        );
        Book bookEntity = bookRepository.findBookById(reservationEntity.getBook().getId()).orElseThrow(
                () -> BaseException.notFound(Book.class.getSimpleName(), "book", String.valueOf(reservationEntity.getBook().getId()))
        );

        Integer quantity  = bookEntity.getQuantityBook().getQuantity();
        bookEntity.getQuantityBook().setQuantity(quantity + 1);

        reservationEntity.setStatus(CANCELLED);
        reservationRepository.save(reservationEntity);
    }

    public List<ReservationResponseAdmin> getUserReservations(Long userId) {
        return reservationMapper.toResponseList(reservationRepository.findAllByReservationByUser(userId));
    }

    public List<ReservationResponseAdmin> getBookReservations(Long bookId) {
        return reservationMapper.toResponseList(reservationRepository.findAllByReservationByBook(bookId));
    }

    public ReservationResponseAdmin getReservationDetails(Long id) {
        return reservationMapper.toDto(reservationRepository.findReservationByIdForDetails(id).orElseThrow(
                () -> BaseException.notFound(Reservation.class.getSimpleName(), "reservation", String.valueOf(id))
        ));
    }

    //private



}
