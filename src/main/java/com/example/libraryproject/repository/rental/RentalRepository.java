package com.example.libraryproject.repository.rental;

import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dao.Rental;
import com.example.libraryproject.model.dao.Reservation;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Query("SELECT r FROM Rental r WHERE r.id = :id AND ( r.rentalStatus = 'ACTIVE' or r.rentalStatus = 'OVERDUE')")
    Optional<Rental> findRentalById(@Param("id") Long id);

    @Query("Select r.book , count (r.book) as usage_count  " +
            "from Rental r " +
            "group by  r.book " +
            "order by usage_count DESC")
    List<Book> findBooksMostRead(Pageable pageable);

    @Query("Select r.book, count(r.book) " +
            "from Rental r " +
            "where r.rentalStartDate > :startDate and r.rentalStartDate < :endDate " +
            "group by r.book")
    List<Object[]> findRentalStatisticsWithCounts(LocalDate startDate, LocalDate endDate);

    @Query("select r from Rental r where r.user.id = :userId")
    List<Rental> findRentalByUserId(Long userId);

    @Query("Select r from Rental r where r.book.id = :bookId")
    List<Rental> findRentalByBookId(Long bookId);

    @Query("select r.book from Rental r where r.book.id = :bookId" +
            " and r.rentalEndDate > :startDate and r.rentalEndDate < :endDate ")
    List<Book> findRentalByBookIdAndDate(Long bookId, LocalDate startDate, LocalDate endDate);

}
