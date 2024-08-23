package com.example.libraryproject.service;

import com.example.libraryproject.mapper.BookMapper;
import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dao.QuantityBook;
import com.example.libraryproject.model.dto.request.create.BookRequestCreate;
import com.example.libraryproject.model.dto.request.update.BookRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.BookAdminResponse;
import com.example.libraryproject.model.dto.response.user.BookUserResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import com.example.libraryproject.repository.BookRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BookService {
    final BookMapper bookMapper;
    final BookRepository bookRepository;
    final AuthorService authorService;
    final PublisherService publisherService;

    public void addBook(BookRequestCreate book) {
        Book bookEntity = bookMapper.toEntity(book);
        QuantityBook quantityBook = new QuantityBook(bookEntity, book.getQuantity());
        bookEntity.setQuantityBook(quantityBook);
        bookEntity.setIsActive(true);
        bookRepository.save(bookEntity);
    }

    public void updateBook(Long id, BookRequestUpdate bookRequest) {
        Book bookEntity = bookRepository.findBookById(id)
                .orElseThrow(
                        () -> new RuntimeException("Book not found")
                );


        bookEntity.setBookName(bookEntity.getBookName() == null ? bookEntity.getBookName() : bookRequest.getBookName());
        bookEntity.setPrice(bookEntity.getPrice() == null ? bookEntity.getPrice() : bookRequest.getPrice());
        bookEntity.setDescription(bookEntity.getDescription() == null ? bookEntity.getDescription() : bookRequest.getDescription());
        bookEntity.setGenre(bookEntity.getGenre() == null ? bookEntity.getGenre() : bookRequest.getGenre());
        bookEntity.setLanguage(bookEntity.getLanguage() == null ? bookEntity.getLanguage() : bookRequest.getLanguage());
        bookEntity.setPages(bookEntity.getPages() == null ? bookEntity.getPages() : bookRequest.getPages());
        bookEntity.setPublicationDate(bookEntity.getPublicationDate() == null ? bookEntity.getPublicationDate() : bookRequest.getPublicationDate());
        bookEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        bookRepository.save(bookEntity);

    }

    public void deleteBook(Long id) {
        Book bookEntity = bookRepository.findBookById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        bookEntity.setIsActive(false);
        bookEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        bookRepository.save(bookEntity);
    }

    public BookAdminResponse getBookById(Long id) {
        Book bookEntity = bookRepository.findBookById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        checkAuthor(bookEntity);
        return bookMapper.toResponse(bookEntity);
    }

    public List<BookAdminResponse> getAllBooks() {
        List<Book> bookEntityList = bookRepository.findAllBook();
        for (Book bookEntity : bookEntityList) {
            checkAuthor(bookEntity);
        }
        return bookMapper.toResponse(bookEntityList);
    }


    // private methods
    private void checkAuthor(Book bookEntity) {
        if (bookEntity.getAuthor() != null) {
             if (!bookEntity.getAuthor().getIsActive()) {
                bookEntity.setAuthor(null);
                bookRepository.save(bookEntity);
            }
        }
    }


}
