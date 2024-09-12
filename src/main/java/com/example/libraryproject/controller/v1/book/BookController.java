package com.example.libraryproject.controller.v1.book;

import com.example.libraryproject.model.dto.request.create.BookRequestCreate;
import com.example.libraryproject.model.dto.request.update.BookRequestUpdate;
import com.example.libraryproject.model.dto.response.payload.BookResponse;
import com.example.libraryproject.model.dto.response.base.BaseResponse;
import com.example.libraryproject.service.book.BookService;
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
    public BaseResponse<Void> addBook(@RequestBody BookRequestCreate book) {
        bookService.addBook(book);
        return BaseResponse.created();
    }

    @PutMapping("/update/{id}")
    public BaseResponse<Void> updateBook(@PathVariable("id") Long id, @RequestBody BookRequestUpdate book) {
        bookService.updateBook(id, book);
        return BaseResponse.success();
    }

    @DeleteMapping("/delete/{id}")
    public BaseResponse<Void> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return BaseResponse.success();
    }

    @GetMapping("/get-book-by-id/{id}")
    public BaseResponse<BookResponse> getBookById(@PathVariable("id") Long id) {
        return BaseResponse.success(bookService.getBookById(id));
    }

    @GetMapping("/get-all-books")
    public BaseResponse<List<BookResponse>> getAllBooks() {
        return BaseResponse.success(bookService.getAllBooks());
    }

}
