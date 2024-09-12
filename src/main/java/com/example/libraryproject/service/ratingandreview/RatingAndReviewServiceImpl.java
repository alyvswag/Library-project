package com.example.libraryproject.service.ratingandreview;

import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.mapper.ratingandreview.RatingAndReviewMapper;
import com.example.libraryproject.model.dao.RatingAndReview;
import com.example.libraryproject.model.dto.request.create.RatAndRevRequestCreate;
import com.example.libraryproject.model.dto.request.update.RatAndRevRequestUpdate;
import com.example.libraryproject.model.dto.response.payload.RatAndRevResponse;
import com.example.libraryproject.model.dto.response.payload.RatingResponse;
import com.example.libraryproject.repository.ratingandreview.RatingAndReviewRepository;
import com.example.libraryproject.service.book.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class RatingAndReviewServiceImpl implements RatingAndReviewService {

    final RatingAndReviewRepository repository;
    final RatingAndReviewMapper mapper;
    final BookService bookService;

    @Override
    public void addRatingAndReview(RatAndRevRequestCreate ratAndRevRequestCreate) {
        RatingAndReview entity = mapper.toEntity(ratAndRevRequestCreate);
        repository.save(entity);
    }

    @Override
    public List<RatAndRevResponse> getRatingsAndReviewsByBook(Long bookId) {
        return mapper.toResponseList(repository.findAllByRatingAndReviewByBook(bookId));
    }

    @Override
    public List<RatAndRevResponse> getRatingsAndReviewsByUser(Long userId) {
        return mapper.toResponseList(repository.findAllByRatingAndReviewByUser(userId));
    }

    @Override
    public void updateRatingAndReview(Long id, RatAndRevRequestUpdate ratAndRevRequestUpdate) {
        RatingAndReview entity = findRatingAndReviewById(id);
        mapper.updateRatingAndReviewFromDto(ratAndRevRequestUpdate, entity);
        repository.save(entity);
    }

    @Override
    public void deleteRatingAndReview(Long id) {
        RatingAndReview entity = findRatingAndReviewById(id);
        entity.setIsActive(false);
        repository.save(entity);
    }

    @Override
    public RatingResponse getAverageRating(Long bookId) {
        bookService.findById(bookId);
        Double rating = repository.averageRatingByBookId(bookId);
        String ratingString = (rating == null ? "0.00" : String.valueOf(rating).substring(0, 3));
        return RatingResponse.builder()
                .averageRating(ratingString)
                .build();
    }

    //private
    private RatingAndReview findRatingAndReviewById(Long id) {
        return repository.findRatingAndReviewById(id).orElseThrow(
                () -> BaseException.notFound(RatingAndReview.class.getSimpleName(), "rating and review", String.valueOf(id))
        );
    }

}
