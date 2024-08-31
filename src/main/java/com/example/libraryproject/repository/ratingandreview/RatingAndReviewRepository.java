package com.example.libraryproject.repository.ratingandreview;

import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dao.RatingAndReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingAndReviewRepository extends JpaRepository<RatingAndReview, Long> {
    @Query("SELECT r FROM RatingAndReview r WHERE r.id = :id AND r.isActive = true")
    Optional<RatingAndReview> findRatingAndReviewById(@Param("id") Long id);

    @Query("select r from RatingAndReview  r where r.book.id = :bookId and r.isActive = true")
    List<RatingAndReview> findAllByRatingAndReviewByBook(@Param("bookId") Long bookId);

    @Query("select r from RatingAndReview  r where r.user.id = :bookId and r.isActive = true")
    List<RatingAndReview> findAllByRatingAndReviewByUser(@Param("bookId") Long userId);

}
