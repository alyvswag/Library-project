package com.example.libraryproject;


import com.example.libraryproject.mapper.book.BookMapper;
import com.example.libraryproject.model.dao.entity.Book;
import com.example.libraryproject.model.dto.request.create.BookRequestCreate;
import com.example.libraryproject.repository.book.BookRepository;
import com.example.libraryproject.service.book.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {
    @Mock
    BookRepository bookRepository;
    @Mock
    BookMapper bookMapper;
    @InjectMocks
    BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addBookTest() {
        BookRequestCreate bookRequestCreate = new BookRequestCreate();
        bookRequestCreate.setQuantity(10);
        Book book = new Book();
        book.setQuantityBook(null);
        when(bookMapper.toEntity(bookRequestCreate)).thenReturn(book);

        bookService.addBook(bookRequestCreate);

        assertNotNull(book.getQuantityBook());
        assertEquals(10, book.getQuantityBook().getQuantity());
        assertEquals(0, book.getQuantityBook().getReservedQuantity());
        assertEquals(0, book.getQuantityBook().getRentalQuantity());

        verify(bookMapper, times(1)).toEntity(bookRequestCreate);  // Mapperin çağırıldığını yoxla
        verify(bookRepository, times(1)).save(book);  //
    }
}

