package com.example.libraryproject.repository.ratingandreview;

import com.example.libraryproject.model.dao.entity.RatingAndReview;
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

    @Query("select r from RatingAndReview  r where r.user.id = :userId and r.isActive = true")
    List<RatingAndReview> findAllByRatingAndReviewByUser(@Param("userId") Long userId);

    @Query("Select AVG(r.rating) from RatingAndReview  r where r.book.id = :bookId")
    Double averageRatingByBookId(@Param("bookId") Long bookId);
}
