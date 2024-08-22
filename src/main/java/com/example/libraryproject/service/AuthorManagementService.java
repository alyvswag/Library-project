package com.example.libraryproject.service;

import com.example.libraryproject.mapper.BookMapper;
import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dto.response.admin.BookAdminResponse;
import com.example.libraryproject.repository.AuthorRepository;
import com.example.libraryproject.repository.BookRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorManagementService {
    final AuthorRepository authorRepository;
    final BookRepository bookRepository;
    final BookMapper bookMapper;

    public void removeBookFromAuthor(Long bookId) {
        Book bookEntity = bookRepository.findBookById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        bookEntity.setAuthor(null);
        bookRepository.save(bookEntity);
    }

    public List<BookAdminResponse> getBooksByAuthorId(Long authorId) {
        List<Book> books = authorRepository.findBooksByAuthor(authorId);
        return bookMapper.toResponse(books);
    }
}
