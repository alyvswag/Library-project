package com.example.libraryproject.mapper.book;

import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dao.QuantityBook;
import com.example.libraryproject.model.dto.response.admin.BookAdminResponse;
import com.example.libraryproject.model.dto.response.admin.QuantityBookAdminResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuantityBookMapper {
    List<QuantityBookAdminResponse> toResponse(List<QuantityBook> quantityBook);

    @Mapping(source = "book", target = "bookAdminResponse")
    QuantityBookAdminResponse toQuantityBookAdminResponse(QuantityBook quantityBook);

    @Mapping(source = "book", target = "bookAdminResponse")
    default BookAdminResponse mapBookToAdminResponse(Book book) {
        if (book == null) {
            return null;
        }
        return BookAdminResponse.builder()
                .id(book.getId())
                .bookName(book.getBookName())
                .price(book.getPrice())
                .description(book.getDescription())
                .genre(book.getGenre())
                .language(book.getLanguage())
                .pages(book.getPages())
                .publicationDate(book.getPublicationDate())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();

        // `BookAdminResponse` sahələrinin `Book` obyektindən əl ilə çevrilməsi
        // todo: kod chatgpt terefinden yazilib daha tekmil versiya eziyyet cek axtar
//            response.setId(book.getId());
//            response.setBookName(book.getBookName());
//            response.setPrice(book.getPrice());
//            response.setDescription(book.getDescription());
//            response.setGenre(book.getGenre());
//            response.setLanguage(book.getLanguage());
//            response.setPages(book.getPages());
//            response.setPublicationDate(book.getPublicationDate());
//            response.setCreatedAt(book.getCreatedAt());
//            response.setUpdatedAt(book.getUpdatedAt());
//            return response;
    }
}

