package com.example.libraryproject.service.author;

import com.example.libraryproject.model.dto.response.payload.BookResponse;

import java.util.List;

public interface AuthorManagementService {
    void removeBookFromAuthor(Long bookId);
    List<BookResponse> getBooksByAuthorId(Long authorId);
}
