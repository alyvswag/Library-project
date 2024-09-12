package com.example.libraryproject.service.author;

import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.mapper.book.BookMapper;
import com.example.libraryproject.model.dao.Author;
import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dto.response.payload.BookResponse;
import com.example.libraryproject.repository.author.AuthorRepository;
import com.example.libraryproject.repository.book.BookRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthorManagementServiceImpl implements AuthorManagementService {
    final AuthorRepository authorRepository;
    final BookRepository bookRepository;
    final BookMapper bookMapper;
    final AuthorService authorService;

    @Override
    public void removeBookFromAuthor(Long bookId) {
        Book bookEntity = bookRepository.findBookById(bookId)
                .orElseThrow(
                        () -> BaseException.notFound(Book.class.getSimpleName(), "book", String.valueOf(bookId))
                );

        if (bookEntity.getAuthor() == null) {
            throw BaseException.notFound(Author.class.getSimpleName(), "author", "for the specified book");
        }
        bookEntity.setAuthor(null);
        bookRepository.save(bookEntity);
    }

    @Override
    public List<BookResponse> getBooksByAuthorId(Long authorId) {
        List<Book> books = bookRepository.findBooksByAuthor(authorId)
                .filter(book -> !book.isEmpty())
                .orElseThrow(() -> BaseException.notFound(Book.class.getSimpleName(), "book", "for the specified author"));
        return bookMapper.toResponse(books);
    }
}
