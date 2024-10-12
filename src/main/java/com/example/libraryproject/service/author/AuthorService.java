package com.example.libraryproject.service.author;

import com.example.libraryproject.model.dao.entity.Author;
import com.example.libraryproject.model.dto.request.create.AuthorRequestCreate;
import com.example.libraryproject.model.dto.request.update.AuthorRequestUpdate;
import com.example.libraryproject.model.dto.response.payload.AuthorResponse;

import java.util.List;

public interface AuthorService {
    void addAuthor(AuthorRequestCreate author);

    void updateAuthor(Long id, AuthorRequestUpdate authorRequest);

    void deleteAuthor(Long id);

    AuthorResponse getAuthorById(Long id);

    List<AuthorResponse> getAllAuthors();

    List<AuthorResponse> getAuthorByName(String name);

    Author findById(Long id);
}
