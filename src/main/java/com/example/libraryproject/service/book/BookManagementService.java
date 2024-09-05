package com.example.libraryproject.service.book;

import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dto.request.filter.BookRequestFilter;
import com.example.libraryproject.model.dto.response.admin.QuantityBookResponseAdmin;
import com.example.libraryproject.model.dto.response.user.BookResponseUser;

import java.util.List;

public interface BookManagementService {
    List<BookResponseUser> searchBooks(String searchWord);

    List<BookResponseUser> filterBooks(BookRequestFilter bookRequest);

    List<QuantityBookResponseAdmin> getBookInventory();

    void reservationQuantity(Book book, Integer value);

    void rentalQuantity(Book book, Integer value);
}
