package com.example.libraryproject.mapper;

import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dto.request.create.BookRequestCreate;
import com.example.libraryproject.model.dto.request.update.BookRequestUpdate;
import com.example.libraryproject.model.dto.response.BookResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toEntity(BookRequestCreate bookRequestCreate);
    Book toEntity(BookRequestUpdate bookRequestUpdate);

    BookResponse toResponse(Book book);
    List<BookResponse> toResponse(List<Book> books);

}
