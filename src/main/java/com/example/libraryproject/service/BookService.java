package com.example.libraryproject.service;

import com.example.libraryproject.mapper.BookMapper;
import com.example.libraryproject.model.dao.Author;
import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dto.request.create.BookRequestCreate;
import com.example.libraryproject.model.dto.request.update.BookRequestUpdate;
import com.example.libraryproject.model.dto.response.AuthorResponse;
import com.example.libraryproject.model.dto.response.BookResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import com.example.libraryproject.repository.BookRepository;

import java.sql.Timestamp;
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
        bookEntity.setIsActive(true);
        bookRepository.save(bookEntity);
    }

    public void updateBook(Long id , BookRequestUpdate bookRequest) {
        Book bookEntity = bookRepository.findBookById(id)
                .orElseThrow(
                ()->new RuntimeException("Book not found")
        );

        bookEntity.setPublisher(bookEntity.getPublisher()==null ? bookEntity.getPublisher() : publisherService.findById(bookRequest.getPublisherId()));
        bookEntity.setAuthor(bookEntity.getAuthor()==null ? bookEntity.getAuthor() : authorService.findById(bookRequest.getAuthorId()));
        bookEntity.setName(bookEntity.getName()==null ? bookEntity.getName() : bookRequest.getName());
        bookEntity.setPrice(bookEntity.getPrice()==null ? bookEntity.getPrice() : bookRequest.getPrice());
        bookEntity.setDescription(bookEntity.getDescription()==null ? bookEntity.getDescription() : bookRequest.getDescription());
        bookEntity.setGenre(bookEntity.getGenre()==null ? bookEntity.getGenre() : bookRequest.getGenre());
        bookEntity.setLanguage(bookEntity.getLanguage()==null ? bookEntity.getLanguage() : bookRequest.getLanguage());
        bookEntity.setPages(bookEntity.getPages()==null ? bookEntity.getPages(): bookRequest.getPages());
        bookEntity.setPublicationDate(bookEntity.getPublicationDate()==null ? bookEntity.getPublicationDate() : bookRequest.getPublicationDate());
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

    public BookResponse getBookById(Long id) {
        Book bookEntity  = bookRepository.findBookById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return bookMapper.toResponse(bookEntity);
    }
    public List<BookResponse> getAllBooks() {
            return  bookMapper.toResponse(bookRepository.findAllBook());
    }

}
