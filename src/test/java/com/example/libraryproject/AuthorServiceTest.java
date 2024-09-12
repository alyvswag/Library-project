package com.example.libraryproject;

import com.example.libraryproject.mapper.author.AuthorMapper;
import com.example.libraryproject.model.dao.Author;
import com.example.libraryproject.model.dto.request.create.AuthorRequestCreate;
import com.example.libraryproject.model.dto.request.update.AuthorRequestUpdate;
import com.example.libraryproject.model.dto.response.payload.AuthorResponse;
import com.example.libraryproject.repository.author.AuthorRepository;
import com.example.libraryproject.service.author.AuthorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorServiceTest {
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private AuthorMapper authorMapper;
    @InjectMocks
    private AuthorServiceImpl authorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAuthor() {
        AuthorRequestCreate authorRequestCreate = new AuthorRequestCreate();
        Author author = new Author();

        when(authorMapper.toEntity(authorRequestCreate)).thenReturn(author);

        authorService.addAuthor(authorRequestCreate);

        verify((authorMapper), times(1)).toEntity(authorRequestCreate);
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    void testUpdateAuthor() {
        Long authorId = 1L;
        AuthorRequestUpdate authorRequestUpdate = new AuthorRequestUpdate();
        Author author = new Author();
        when(authorRepository.findAuthorById(authorId)).thenReturn(Optional.of(author));
        authorService.updateAuthor(authorId, authorRequestUpdate);
        verify((authorMapper), times(1)).toEntity(authorRequestUpdate);
        verify(authorRepository, times(1)).save(author);
        verify(authorRepository, times(1)).findAuthorById(authorId);
    }

    @Test
    void testDeleteAuthor() {
        Long authorId = 1L;
        Author author = new Author();
        author.setIsActive(true);
        when(authorRepository.findAuthorById(authorId)).thenReturn(Optional.of(author));
        authorService.deleteAuthor(authorId);
        assertFalse(author.getIsActive());//tesdiqleyiremki heqiqeten false qaytarib
        verify(authorRepository, times(1)).findAuthorById(authorId);
    }

    @Test
    void testGetAuthorById() {
        Long authorId = 1L;
        Author author = new Author();
        AuthorResponse authorResponse = new AuthorResponse();
        when(authorRepository.findAuthorById(authorId)).thenReturn(Optional.of(author));
        when(authorMapper.toResponse(author)).thenReturn(authorResponse);
        AuthorResponse result = authorService.getAuthorById(authorId);
        assertEquals(authorResponse, result);
        verify(authorRepository, times(1)).findAuthorById(authorId);
        verify(authorMapper, times(1)).toResponse(author);
    }
    @Test
    void testGetAllAuthors() {
        List<Author> authors = new ArrayList<>();
        List<AuthorResponse> authorResponses = new ArrayList<>();
        when(authorRepository.findAllAuthor()).thenReturn(authors);
        when(authorMapper.toResponse(authors)).thenReturn(authorResponses);
        List<AuthorResponse> result = authorService.getAllAuthors();
        assertEquals(authorResponses, result);
        verify(authorRepository, times(1)).findAllAuthor();
        verify(authorMapper, times(1)).toResponse(authors);
    }
    @Test
    void testGetAuthorsByName() {

    }

}
