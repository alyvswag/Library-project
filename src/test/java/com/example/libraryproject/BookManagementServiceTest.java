package com.example.libraryproject;

import com.example.libraryproject.mapper.book.BookMapper;
import com.example.libraryproject.mapper.book.QuantityBookMapper;
import com.example.libraryproject.model.dao.entity.Book;
import com.example.libraryproject.model.dao.entity.QuantityBook;
import com.example.libraryproject.model.dto.request.filter.BookRequestFilter;
import com.example.libraryproject.model.dto.response.payload.BookResponse;
import com.example.libraryproject.model.dto.response.payload.QuantityBookResponse;
import com.example.libraryproject.repository.book.BookRepository;
import com.example.libraryproject.repository.book.QuantityBookRepository;
import com.example.libraryproject.service.book.BookManagementServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.example.libraryproject.model.enums.book.Genre.QORXU;
import static com.example.libraryproject.model.enums.book.Language.AZ;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookManagementServiceTest {
    @Mock
    BookMapper bookMapper;
    @Mock
    BookRepository bookRepository;
    @Mock
    private CriteriaBuilder cb;
    @Mock
    private CriteriaQuery<Book> query;
    @Mock
    private Root<Book> root;
    @Mock
    private TypedQuery<Book> typedQuery;
    @Mock
    EntityManager em;
    @Mock
    QuantityBookRepository quantityBookRepository;
    @Mock
    QuantityBookMapper quantityBookMapper;

    @InjectMocks
    BookManagementServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void searchBooksTest() {
        String searchWords = "kitab";
        List<Book> books = new ArrayList<>();
        List<BookResponse> bookResponse = new ArrayList<>();
        when(bookRepository.searchBook(searchWords)).thenReturn(books);
        when(bookMapper.toResponse(books)).thenReturn(bookResponse);
        List<BookResponse> result = bookService.searchBooks(searchWords);
        assertEquals(result, bookResponse);
        verify(bookRepository, times(1)).searchBook(searchWords);
        verify(bookMapper, times(1)).toResponse(books);
    }

    @Test
    void filterBooksTest() {
        BookRequestFilter bookRequest = new BookRequestFilter();
        bookRequest.setMinPrice(BigDecimal.valueOf(10));
        bookRequest.setMaxPrice(BigDecimal.valueOf(20));
        bookRequest.setMinPages(BigDecimal.valueOf(10));
        bookRequest.setMaxPages(BigDecimal.valueOf(20));
        bookRequest.setGenre(QORXU);
        bookRequest.setLanguage(AZ);
        List<Book> books = List.of(new Book());
        List<BookResponse> responseBooks = List.of(new BookResponse());

        when(em.getCriteriaBuilder()).thenReturn(cb);
        when(cb.createQuery(Book.class)).thenReturn(query);
        when(query.from(Book.class)).thenReturn(root);

        List<Predicate> predicates = new ArrayList<>();
        when(cb.like(root.get("status"), "%" + "ACTIVE" + "%")).thenReturn(mock(Predicate.class));

        when(cb.greaterThanOrEqualTo(root.get("price"), bookRequest.getMinPrice())).thenReturn(mock(Predicate.class));
        when(cb.lessThanOrEqualTo(root.get("price"), bookRequest.getMaxPrice())).thenReturn(mock(Predicate.class));
        when(cb.like(root.get("genre"), "%" + bookRequest.getGenre() + "%")).thenReturn(mock(Predicate.class));
        when(cb.like(root.get("language"), "%" + bookRequest.getLanguage() + "%")).thenReturn(mock(Predicate.class));
        when(cb.greaterThanOrEqualTo(root.get("pages"), bookRequest.getMinPages())).thenReturn(mock(Predicate.class));
        when(cb.lessThanOrEqualTo(root.get("pages"), bookRequest.getMaxPages())).thenReturn(mock(Predicate.class));

        when(em.createQuery(query)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(books);

        when(bookMapper.toResponse(books)).thenReturn(responseBooks);

        List<BookResponse> result = bookService.filterBooks(bookRequest);

        assertEquals(responseBooks, result);
        verify(em, times(1)).getCriteriaBuilder();
        verify(typedQuery, times(1)).getResultList();
        verify(bookMapper, times(1)).toResponse(books);

    }
    @Test
    void getBookInventoryTest(){
        List<QuantityBook> quantityBooks = new ArrayList<>();
        List<QuantityBookResponse> quantityBookResponses = new ArrayList<>();

        when(quantityBookRepository.findAll()).thenReturn(quantityBooks);
        when(quantityBookMapper.toResponse(quantityBooks)).thenReturn(quantityBookResponses);
        List<QuantityBookResponse> result = bookService.getBookInventory();
        assertEquals(quantityBookResponses, result);
        verify(quantityBookRepository, times(1)).findAll();
        verify(quantityBookMapper, times(1)).toResponse(quantityBooks);
    }
    @Test
    void reservationQuantity(){
        Book book = new Book();
        QuantityBook quantityBook = new QuantityBook();
        quantityBook.setQuantity(10);
        quantityBook.setBook(book);
        quantityBook.setReservedQuantity(0);
        quantityBook.setRentalQuantity(0);
        book.setQuantityBook(quantityBook);

        Integer value = 5;

        bookService.reservationQuantity(book, value);

        assertEquals(value, book.getQuantityBook().getReservedQuantity());
        verify(bookRepository, times(1)).save(book);
    }
    @Test
    void rentalQuantityTest() {
        Book book = new Book();
        QuantityBook quantityBook = new QuantityBook();
        quantityBook.setQuantity(10);
        quantityBook.setReservedQuantity(0);
        quantityBook.setRentalQuantity(0);
        book.setQuantityBook(quantityBook);

        Integer value = 3;
        bookService.rentalQuantity(book, value);


        assertEquals(3, book.getQuantityBook().getRentalQuantity());
        verify(bookRepository, times(1)).save(book);
    }
}
