package com.example.libraryproject.controller.v1.ratingandreview;

import com.example.libraryproject.model.dto.request.create.RatAndRevRequestCreate;
import com.example.libraryproject.model.dto.request.update.RatAndRevRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.RatAndRevResponseAdmin;
import com.example.libraryproject.model.dto.response.admin.RatingResponse;
import com.example.libraryproject.model.dto.response.base.BaseResponse;
import com.example.libraryproject.service.ratingandreview.RatingAndReviewService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rating-and-review")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RatingAndReviewController {
    final RatingAndReviewService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add-rating-and-review")
    public BaseResponse<Void> addRatingAndReview(@RequestBody RatAndRevRequestCreate ratingAndReview) {
        service.addRatingAndReview(ratingAndReview);
        return BaseResponse.created();
    }

    @GetMapping("/get-ratings-and-reviews-book/{bookId}")
    public BaseResponse<List<RatAndRevResponseAdmin>> getRatingsAndReviewsByBook(@PathVariable Long bookId) {
        return BaseResponse.success(service.getRatingsAndReviewsByBook(bookId));
    }

    @GetMapping("/get-ratings-and-reviews-user/{userId}")
    public BaseResponse<List<RatAndRevResponseAdmin>> getRatingsAndReviewsByUser(@PathVariable Long userId) {
        return BaseResponse.success(service.getRatingsAndReviewsByUser(userId));
    }

    @PutMapping("/update-rating-and-review/{id}")
    public BaseResponse<Void> updateRatingAndReview(@PathVariable Long id, @RequestBody RatAndRevRequestUpdate ratingAndReview) {
        service.updateRatingAndReview(id, ratingAndReview);
        return BaseResponse.success();
    }

    @DeleteMapping("/delete-rating-and-review/{id}")
    public BaseResponse<Void> deleteRatingAndReview(@PathVariable Long id) {
        service.deleteRatingAndReview(id);
        return BaseResponse.success();
    }

    @GetMapping("/get-average-rating/{bookId}")
    public BaseResponse<RatingResponse> getAverageRating(@PathVariable Long bookId) {
        return BaseResponse.success(service.getAverageRating(bookId));
    }


}
