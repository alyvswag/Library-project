package com.example.libraryproject.service.author;

import com.example.libraryproject.model.dto.response.admin.BookResponseAdmin;

import java.util.List;

public interface AuthorManagementService {
    void removeBookFromAuthor(Long bookId);
    List<BookResponseAdmin> getBooksByAuthorId(Long authorId);
}
