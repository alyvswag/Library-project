package com.example.libraryproject;


import com.example.libraryproject.mapper.book.BookMapper;
import com.example.libraryproject.model.dao.Author;
import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dto.response.payload.BookResponse;
import com.example.libraryproject.repository.author.AuthorRepository;
import com.example.libraryproject.repository.book.BookRepository;
import com.example.libraryproject.service.author.AuthorManagementService;
import com.example.libraryproject.service.author.AuthorManagementServiceImpl;
import com.example.libraryproject.service.author.AuthorService;
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

class AuthorManagementServiceTest {
    @Mock
    AuthorRepository authorRepository;
    @Mock
    AuthorService authorService;
    @Mock
    BookMapper bookMapper;
    @Mock
    BookRepository bookRepository;
    @InjectMocks
    AuthorManagementServiceImpl authorManagementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void removeBookFromAuthorTest() {
        Long id = 1L;
        Book book = new Book();
        Author author = new Author();
        book.setAuthor(author);

        when(bookRepository.findBookById(id)).thenReturn(Optional.of(book));
        authorManagementService.removeBookFromAuthor(id);
        assertNull(book.getAuthor());
        verify((bookRepository), times(1)).findBookById(id);

    }
    @Test
    void getBooksByAuthorIdTest(){
        Long id = 1L;
        List<Book> books = new ArrayList<>();
        books.add(new Book());
        List<BookResponse> bookResponses = new ArrayList<>();
        when(bookRepository.findBooksByAuthor(id)).thenReturn(Optional.of(books));
        when(bookMapper.toResponse(books)).thenReturn(bookResponses);
        List<BookResponse> result = authorManagementService.getBooksByAuthorId(id);
        assertEquals(result, bookResponses);
        verify(bookRepository, times(1)).findBooksByAuthor(id);
        verify(bookMapper, times(1)).toResponse(books);
    }


}
