package com.example.libraryproject.service.reservation;

import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.mapper.reservation.ReservationMapper;
import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dao.Reservation;
import com.example.libraryproject.model.dao.User;
import com.example.libraryproject.model.dto.request.create.ReservationRequestCreate;
import com.example.libraryproject.model.dto.request.update.ReservationRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.ReservationResponseAdmin;
import com.example.libraryproject.repository.book.BookRepository;
import com.example.libraryproject.repository.rental.RentalRepository;
import com.example.libraryproject.repository.reservation.ReservationRepository;
import com.example.libraryproject.repository.user.UserRepository;
import com.example.libraryproject.service.book.BookManagementService;
import com.example.libraryproject.service.book.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.libraryproject.model.enums.reservation.RezervStatus.CANCELLED;
import static com.example.libraryproject.utils.CommonUtils.throwIf;
import static com.example.libraryproject.model.enums.response.ErrorResponseMessages.BOOK_UNAVAILABLE_QUANTITY;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    final ReservationRepository reservationRepository;
    final ReservationMapper reservationMapper;
    final BookRepository bookRepository;
    final UserRepository userRepository;
    final BookManagementService bookManagementService;
    private final BookService bookService;
    private final RentalRepository rentalRepository;

    @Override
    public synchronized void addReservation(ReservationRequestCreate request) {
        Reservation reservationEntity = reservationMapper.toEntity(request);
        Book bookEntity = reservationEntity.getBook();

        throwIf(() -> !checkBookAvailability(bookEntity), BaseException.of(BOOK_UNAVAILABLE_QUANTITY));

        bookManagementService.reservationQuantity(bookEntity, 1);
        reservationRepository.save(reservationEntity);
    }

    @Override
    public void updateReservation(Long id, ReservationRequestUpdate request) {
        Reservation reservationEntity = findReservationById(id);
        reservationMapper.updateReservationFromDto(request, reservationEntity);
        reservationRepository.save(reservationEntity);
    }

    @Override
    public synchronized void cancelReservation(Long id) {
        Reservation reservationEntity = findReservationById(id);

        Book bookEntity = reservationEntity.getBook();
        bookManagementService.reservationQuantity(bookEntity, -1);

        reservationEntity.setStatus(CANCELLED);
        reservationRepository.save(reservationEntity);
    }

    @Override
    public List<ReservationResponseAdmin> getUserReservations(Long userId) {
        userRepository.findUserById(userId)
                .orElseThrow(
                        () -> BaseException.notFound(User.class.getSimpleName(), "user", String.valueOf(userId))
                );
        return reservationMapper.toResponseList(reservationRepository.findAllByReservationByUser(userId));
    }

    @Override
    public List<ReservationResponseAdmin> getBookReservations(Long bookId) {
        bookRepository.findBookById(bookId)
                .orElseThrow(
                        () -> BaseException.notFound(Book.class.getSimpleName(), "book", String.valueOf(bookId))
                );
        return reservationMapper.toResponseList(reservationRepository.findAllByReservationByBook(bookId));
    }

    @Override
    public ReservationResponseAdmin getReservationDetails(Long id) {
        return reservationMapper.toDto(reservationRepository.findReservationByIdForDetails(id).orElseThrow(
                () -> BaseException.notFound(Reservation.class.getSimpleName(), "reservation", String.valueOf(id))
        ));
    }

    @Override
    public Boolean checkAvailability(Long bookId, LocalDate startDate, LocalDate endDate) {
        bookService.findById(bookId);
        return !(rentalRepository.findRentalByBookIdAndDate(bookId, startDate, endDate).isEmpty());

    }

    //private

    private synchronized boolean checkBookAvailability(Book book) {
        return book.getQuantityBook().getQuantity()
                - book.getQuantityBook().getReservedQuantity()
                - book.getQuantityBook().getRentalQuantity() > 0;
    }
    private Reservation findReservationById(Long id) {
        return reservationRepository.findReservationById(id).orElseThrow(
                () -> BaseException.notFound(Reservation.class.getSimpleName(), "reservation", String.valueOf(id))
        );
    }

}
