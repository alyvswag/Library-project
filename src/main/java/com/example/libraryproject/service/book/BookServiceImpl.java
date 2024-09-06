package com.example.libraryproject.service.book;

import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.mapper.book.BookMapper;
import com.example.libraryproject.model.dao.Author;
import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dao.Publisher;
import com.example.libraryproject.model.dao.QuantityBook;
import com.example.libraryproject.model.dto.request.create.BookRequestCreate;
import com.example.libraryproject.model.dto.request.update.BookRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.BookResponseAdmin;
import com.example.libraryproject.service.publisher.PublisherService;
import com.example.libraryproject.service.author.AuthorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.libraryproject.repository.book.BookRepository;

import java.sql.Timestamp;
import java.util.List;

import static com.example.libraryproject.model.enums.base.Status.ACTIVE;
import static com.example.libraryproject.model.enums.base.Status.DELETED;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class BookServiceImpl implements BookService {
    final BookMapper bookMapper;
    final BookRepository bookRepository;
    final AuthorService authorService;
    final PublisherService publisherService;

    @Override
    public void addBook(BookRequestCreate book) {
        Book bookEntity = bookMapper.toEntity(book);
        checkAuthorAndPublisher(bookEntity);
        bookEntity.setQuantityBook(QuantityBook.builder()
                .book(bookEntity)
                .quantity(book.getQuantity())
                .reservedQuantity(0)
                .rentalQuantity(0) //rucnoy daxil etme
                .build());
        bookRepository.save(bookEntity);
    }

    @Override
    public void updateBook(Long id, BookRequestUpdate bookRequest) {
        Book bookEntity = findBookById(id);
        bookMapper.updateBookFromDto(bookRequest, bookEntity);
        bookRepository.save(bookEntity);
    }

    @Override
    public void deleteBook(Long id) {
        Book bookEntity = findBookById(id);

        bookEntity.setStatus(DELETED);
        bookEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        bookRepository.save(bookEntity);
    }

    @Override
    public BookResponseAdmin getBookById(Long id) {
        Book bookEntity = findBookById(id);
        checkAuthor(bookEntity);
        return bookMapper.toResponse(bookEntity);
    }

    @Override
    public List<BookResponseAdmin> getAllBooks() {
        List<Book> bookEntityList = bookRepository.findAllBook();
        for (Book bookEntity : bookEntityList) {
            checkAuthor(bookEntity);
        }
        return bookMapper.toResponse(bookEntityList);
    }

    @Override
    // public methods
    public Book findById(Long id) {
        if (id == null) {
            throw BaseException.nullNotAllowed(Book.class.getSimpleName());
        }
        return bookRepository.findActiveBookById(id)
                .orElseThrow(() -> BaseException.notFound(Book.class.getSimpleName(), "book", String.valueOf(id)));
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

    private void checkAuthorAndPublisher(Book bookEntity) {
        if (bookEntity.getAuthor() != null) {
            if (!bookEntity.getAuthor().getIsActive())
                throw BaseException.notFound(Author.class.getSimpleName(), "author", bookEntity.getAuthor().getId());
        }
        if (bookEntity.getPublisher() != null) {
            if (!bookEntity.getPublisher().getIsActive())
                throw BaseException.notFound(Publisher.class.getSimpleName(), "publisher", bookEntity.getPublisher().getId());
        }
    }

    private Book findBookById(Long id) {
        return bookRepository.findBookById(id)
                .orElseThrow(
                        () -> BaseException.notFound(Book.class.getSimpleName(), "book", String.valueOf(id))
                );
    }
}
