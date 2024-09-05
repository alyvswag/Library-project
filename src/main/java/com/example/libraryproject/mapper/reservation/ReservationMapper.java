package com.example.libraryproject.mapper.reservation;

import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dao.Reservation;
import com.example.libraryproject.model.dao.User;
import com.example.libraryproject.model.dto.request.create.ReservationRequestCreate;
import com.example.libraryproject.model.dto.request.update.ReservationRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.ReservationResponseAdmin;
import com.example.libraryproject.service.book.BookService;
import com.example.libraryproject.service.user.UserService;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserService.class, BookService.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReservationMapper {
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "user", source = "userId")
    @Mapping(target = "book", source = "bookId")
    Reservation toEntity(ReservationRequestCreate reservationRequestCreate);

    default User mapUser(Long userId, @Context UserService userService) {
        return userService.findById(userId);
    }

    default Book mapBook(Long bookId, @Context BookService bookService) {
        return bookService.findById(bookId);
    }

    void updateReservationFromDto(ReservationRequestUpdate dto, @MappingTarget Reservation reservation);

    List<ReservationResponseAdmin> toResponseList(List<Reservation> reservationList);

    ReservationResponseAdmin toDto(Reservation reservation);
}
