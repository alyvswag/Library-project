package com.example.libraryproject.repository.reservation;

import com.example.libraryproject.model.dao.RatingAndReview;
import com.example.libraryproject.model.dao.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r WHERE r.id = :id  ")
    Optional<Reservation> findReservationByIdForDetails(@Param("id") Long id);

    @Query("SELECT r FROM Reservation r WHERE r.id = :id AND r.status = 'PENDING' ")
    Optional<Reservation> findReservationById(@Param("id") Long id);

    @Query("select r from Reservation  r where r.user.id = :userId and r.status = 'PENDING' ")
    List<Reservation> findAllByReservationByUser(@Param("userId") Long userId);

    @Query("select r from Reservation  r where r.book.id = :bookId and r.status = 'PENDING' ")
    List<Reservation> findAllByReservationByBook(@Param("bookId") Long bookId);
}
