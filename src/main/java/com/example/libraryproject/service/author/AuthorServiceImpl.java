package com.example.libraryproject.service.author;

import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.mapper.author.AuthorMapper;
import com.example.libraryproject.model.dao.Author;
import com.example.libraryproject.model.dto.request.create.AuthorRequestCreate;
import com.example.libraryproject.model.dto.request.update.AuthorRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.AuthorResponseAdmin;
import com.example.libraryproject.repository.author.AuthorRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorServiceImpl implements AuthorService {

    final AuthorMapper authorMapper;
    final AuthorRepository authorRepository;

    @Override
    public void addAuthor(AuthorRequestCreate author) {
        Author authorEntity = authorMapper.toEntity(author);
        authorRepository.save(authorEntity);
    }

    @Override
    public void updateAuthor(Long id, AuthorRequestUpdate authorRequest) {
        Author authorEntity = findAuthorById(id);
        authorMapper.updateAuthorFromDto(authorRequest, authorEntity);
        authorRepository.save(authorEntity);
    }

    @Override
    public void deleteAuthor(Long id) {
        Author authorEntity = findAuthorById(id);

        authorEntity.setIsActive(false);
        authorRepository.save(authorEntity);
    }

    @Override
    public AuthorResponseAdmin getAuthorById(Long id) {
        Author authorEntity = findAuthorById(id);
        return authorMapper.toResponse(authorEntity);
    }

    @Override
    public List<AuthorResponseAdmin> getAllAuthors() {
        return authorMapper.toResponse(authorRepository.findAllAuthor());
        // fikrimce burda exception atmaga deymez mence her zaman cedvelde melumat olacaq
    }

    @Override
    public List<AuthorResponseAdmin> getAuthorByName(String name) {
        List<Author> authorEntities = authorRepository.findAuthorByName(name)
                .filter(authors -> !authors.isEmpty())
                .orElseThrow(() -> BaseException.notFound(Author.class.getSimpleName(), "author", name));

        return authorMapper.toResponse(authorEntities);
    }

    @Override
    //public
    public Author findById(Long id) {
        if (id == null) {
            throw BaseException.nullNotAllowed(Author.class.getSimpleName());
        }
        return authorRepository.findById(id)
                .orElseThrow(() -> BaseException.notFound(Author.class.getSimpleName(), "author", String.valueOf(id)));
    }

    //private
    private Author findAuthorById(Long id) {
        return authorRepository.findAuthorById(id)
                .orElseThrow(() -> BaseException.notFound(Author.class.getSimpleName(), "author", String.valueOf(id)));
    }


}