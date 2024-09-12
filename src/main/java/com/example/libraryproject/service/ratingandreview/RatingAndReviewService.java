package com.example.libraryproject.service.ratingandreview;

import com.example.libraryproject.model.dto.request.create.RatAndRevRequestCreate;
import com.example.libraryproject.model.dto.request.update.RatAndRevRequestUpdate;
import com.example.libraryproject.model.dto.response.payload.RatAndRevResponse;
import com.example.libraryproject.model.dto.response.payload.RatingResponse;

import java.util.List;

public interface RatingAndReviewService {
    void addRatingAndReview(RatAndRevRequestCreate ratAndRevRequestCreate);
    List<RatAndRevResponse> getRatingsAndReviewsByBook(Long bookId);
    List<RatAndRevResponse> getRatingsAndReviewsByUser(Long userId);
    void updateRatingAndReview(Long id, RatAndRevRequestUpdate ratAndRevRequestUpdate);
    void deleteRatingAndReview(Long id);
    RatingResponse getAverageRating(Long bookId);

}
