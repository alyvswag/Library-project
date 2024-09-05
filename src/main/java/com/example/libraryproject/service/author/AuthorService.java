package com.example.libraryproject.service.author;

import com.example.libraryproject.model.dao.Author;
import com.example.libraryproject.model.dto.request.create.AuthorRequestCreate;
import com.example.libraryproject.model.dto.request.update.AuthorRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.AuthorResponseAdmin;

import java.util.List;

public interface AuthorService {
    void addAuthor(AuthorRequestCreate author);

    void updateAuthor(Long id, AuthorRequestUpdate authorRequest);

    void deleteAuthor(Long id);

    AuthorResponseAdmin getAuthorById(Long id);

    List<AuthorResponseAdmin> getAllAuthors();

    List<AuthorResponseAdmin> getAuthorByName(String name);

    Author findById(Long id);
}
