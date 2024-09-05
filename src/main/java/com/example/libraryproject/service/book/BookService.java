package com.example.libraryproject.service.book;

import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dto.request.create.BookRequestCreate;
import com.example.libraryproject.model.dto.request.update.BookRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.BookResponseAdmin;

import java.util.List;

public interface BookService {
    void addBook(BookRequestCreate book);

    void updateBook(Long id, BookRequestUpdate bookRequest);

    void deleteBook(Long id);

    BookResponseAdmin getBookById(Long id);

    List<BookResponseAdmin> getAllBooks();

    Book findById(Long id);
}
