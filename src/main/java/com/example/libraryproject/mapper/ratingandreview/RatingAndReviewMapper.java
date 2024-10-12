package com.example.libraryproject.mapper.ratingandreview;

import com.example.libraryproject.model.dao.entity.Book;
import com.example.libraryproject.model.dao.entity.RatingAndReview;
import com.example.libraryproject.model.dao.entity.User;
import com.example.libraryproject.model.dto.request.create.RatAndRevRequestCreate;
import com.example.libraryproject.model.dto.request.update.RatAndRevRequestUpdate;
import com.example.libraryproject.model.dto.response.payload.RatAndRevResponse;
import com.example.libraryproject.service.book.BookService;
import com.example.libraryproject.service.user.UserService;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserService.class, BookService.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RatingAndReviewMapper {
    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "user", source = "userId")
    @Mapping(target = "book", source = "bookId")
    RatingAndReview toEntity(RatAndRevRequestCreate ratAndRevRequestCreate);

    default User mapUser(Long userId, @Context UserService userService) {
        return userService.findById(userId);
    }

    default Book mapBook(Long bookId, @Context BookService bookService) {
        return bookService.findById(bookId);
    }

    List<RatAndRevResponse> toResponseList(List<RatingAndReview> ratingAndReviewList);

    void updateRatingAndReviewFromDto(RatAndRevRequestUpdate dto, @MappingTarget RatingAndReview ratingAndReview);

}
