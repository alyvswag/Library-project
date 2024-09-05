package com.example.libraryproject.service.ratingandreview;

import com.example.libraryproject.model.dto.request.create.RatAndRevRequestCreate;
import com.example.libraryproject.model.dto.request.update.RatAndRevRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.RatAndRevResponseAdmin;
import com.example.libraryproject.model.dto.response.admin.RatingResponse;

import java.util.List;

public interface RatingAndReviewService {
    void addRatingAndReview(RatAndRevRequestCreate ratAndRevRequestCreate);
    List<RatAndRevResponseAdmin> getRatingsAndReviewsByBook(Long bookId);
    List<RatAndRevResponseAdmin> getRatingsAndReviewsByUser(Long userId);
    void updateRatingAndReview(Long id, RatAndRevRequestUpdate ratAndRevRequestUpdate);
    void deleteRatingAndReview(Long id);
    RatingResponse getAverageRating(Long bookId);

}
