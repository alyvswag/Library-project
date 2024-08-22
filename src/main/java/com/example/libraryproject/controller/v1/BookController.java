package com.example.libraryproject.controller.v1;

import com.example.libraryproject.model.dto.request.create.BookRequestCreate;
import com.example.libraryproject.model.dto.request.update.BookRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.BookAdminResponse;
import com.example.libraryproject.model.dto.response.user.BookUserResponse;
import com.example.libraryproject.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BookController {
    final BookService bookService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public void addBook(@RequestBody BookRequestCreate book) {
        bookService.addBook(book);
    }

    @PutMapping("/update/{id}")
    public void updateBook(@PathVariable("id") Long id, @RequestBody BookRequestUpdate book) {
        bookService.updateBook(id, book);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/getBookById/{id}")
    public BookAdminResponse getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/getAllBooks")
    public List<BookAdminResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

}
