package com.example.libraryproject.service.author;

import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.mapper.author.AuthorMapper;
import com.example.libraryproject.model.dao.Author;
import com.example.libraryproject.model.dto.request.create.AuthorRequestCreate;
import com.example.libraryproject.model.dto.request.update.AuthorRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.AuthorAdminResponse;
import com.example.libraryproject.repository.author.AuthorRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorService {
    final AuthorMapper authorMapper;
    final AuthorRepository authorRepository;


    public void addAuthor(AuthorRequestCreate author) {
        Author authorEntity = authorMapper.toEntity(author);
        authorEntity.setIsActive(true);
        authorRepository.save(authorEntity);
    }

    public void updateAuthor(Long id, AuthorRequestUpdate authorRequest) {
        Author authorEntity = authorRepository.findAuthorById(id)
                .orElseThrow(() -> BaseException.notFound(Author.class.getSimpleName(), "author", String.valueOf(id)));

        authorEntity.setAuthorName(authorRequest.getAuthorName() == null ? authorEntity.getAuthorName() : authorRequest.getAuthorName());
        authorEntity.setAuthorSurname(authorRequest.getAuthorSurname() == null ? authorEntity.getAuthorSurname() : authorRequest.getAuthorSurname());
        authorEntity.setDescription(authorRequest.getDescription() == null ? authorEntity.getDescription() : authorRequest.getDescription());
        authorEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        authorRepository.save(authorEntity);

    }

    public void deleteAuthor(Long id) {
        Author authorEntity = authorRepository.findAuthorById(id)
                .orElseThrow(() -> BaseException.notFound(Author.class.getSimpleName(), "author", String.valueOf(id)));

        authorEntity.setIsActive(false);
        authorEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        authorRepository.save(authorEntity);

    }

    public AuthorAdminResponse getAuthorById(Long id) {
        Author authorEntity = authorRepository.findAuthorById(id)
                .orElseThrow(() -> BaseException.notFound(Author.class.getSimpleName(), "author", String.valueOf(id)));

        return authorMapper.toResponse(authorEntity);

    }

    public List<AuthorAdminResponse> getAllAuthors() {
        return authorMapper.toResponse(authorRepository.findAllAuthor());
        // fikrimce burda exception atmaga deymez mence her zaman cedvelde melumat olacaq
    }

    public List<AuthorAdminResponse> getAuthorByName(String name) {
        List<Author> authorEntities = authorRepository.findAuthorByName(name)
                .filter(authors -> !authors.isEmpty())
                .orElseThrow(() -> BaseException.notFound(Author.class.getSimpleName(), "author", name));

        return authorMapper.toResponse(authorEntities);
    }


    //
    public Author findById(Long id) {
        return id == null ? null : authorRepository.findById(id)
                .orElseThrow(() -> BaseException.notFound(Author.class.getSimpleName(), "author", String.valueOf(id)));
    }


}