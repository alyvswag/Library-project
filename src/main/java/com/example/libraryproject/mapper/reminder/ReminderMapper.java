package com.example.libraryproject.mapper.reminder;

import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dao.Reminder;
import com.example.libraryproject.model.dao.User;
import com.example.libraryproject.model.dto.request.create.ReminderRequestCreate;
import com.example.libraryproject.model.dto.response.payload.ReminderResponse;
import com.example.libraryproject.service.book.BookService;
import com.example.libraryproject.service.user.UserService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserService.class, BookService.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReminderMapper {
    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "user", source = "userId")
    @Mapping(target = "book", source = "bookId")
    Reminder toEntity(ReminderRequestCreate reminderRequestCreate);

    default User mapUser(Long userId, @Context UserService userService) {
        return userService.findById(userId);
    }

    default Book mapBook(Long bookId, @Context BookService bookService) {
        return bookService.findById(bookId);
    }

//    List<ReminderResponseUser> toResponseUser(List<Reminder> reminders);//silinecek

    List<ReminderResponse> toResponse(List<Reminder> reminders);

}
