package com.example.libraryproject.service.ratingandreview;

import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.mapper.ratingandreview.RatingAndReviewMapper;
import com.example.libraryproject.model.dao.RatingAndReview;
import com.example.libraryproject.model.dto.request.create.RatAndRevRequestCreate;
import com.example.libraryproject.model.dto.request.update.RatAndRevRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.RatAndRevResponseAdmin;
import com.example.libraryproject.model.dto.response.admin.RatingResponse;
import com.example.libraryproject.repository.ratingandreview.RatingAndReviewRepository;
import com.example.libraryproject.service.book.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RatingAndReviewService {

    final RatingAndReviewRepository repository;
    final RatingAndReviewMapper mapper;
    final BookService bookService;

    public void addRatingAndReview(RatAndRevRequestCreate ratAndRevRequestCreate) {
        RatingAndReview entity = mapper.toEntity(ratAndRevRequestCreate);
        repository.save(entity);
    }

    public List<RatAndRevResponseAdmin> getRatingsAndReviewsByBook(Long bookId) {
        return mapper.toResponseList(repository.findAllByRatingAndReviewByBook(bookId));
    }

    public List<RatAndRevResponseAdmin> getRatingsAndReviewsByUser(Long userId) {
        return mapper.toResponseList(repository.findAllByRatingAndReviewByUser(userId));
    }

    public void updateRatingAndReview(Long id, RatAndRevRequestUpdate ratAndRevRequestUpdate) {
        RatingAndReview entity = repository.findRatingAndReviewById(id).orElseThrow(
                () -> BaseException.notFound(RatingAndReview.class.getSimpleName(), "rating and review", String.valueOf(id))
        );
        mapper.updateRatingAndReviewFromDto(ratAndRevRequestUpdate, entity);
        repository.save(entity);
    }

    public void deleteRatingAndReview(Long id) {
        RatingAndReview entity = repository.findRatingAndReviewById(id).orElseThrow(
                () -> BaseException.notFound(RatingAndReview.class.getSimpleName(), "rating and review", String.valueOf(id))
        );
        entity.setIsActive(false);
        repository.save(entity);
    }

    public RatingResponse getAverageRating(Long bookId) {
        bookService.findById(bookId);
        Double rating = repository.averageRatingByBookId(bookId);
        String ratingString = (rating == null ? "0.00" : String.valueOf(rating).substring(0, 4));
        return RatingResponse.builder()
                .averageRating(ratingString)
                .build();
    }

}