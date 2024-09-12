package com.example.libraryproject.service.book;

import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dto.request.filter.BookRequestFilter;
import com.example.libraryproject.model.dto.response.payload.BookResponse;
import com.example.libraryproject.model.dto.response.payload.QuantityBookResponse;

import java.util.List;

public interface BookManagementService {
    List<BookResponse> searchBooks(String searchWord);

    List<BookResponse> filterBooks(BookRequestFilter bookRequest);

    List<QuantityBookResponse> getBookInventory();

    void reservationQuantity(Book book, Integer value);

    void rentalQuantity(Book book, Integer value);
}
