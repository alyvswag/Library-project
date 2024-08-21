package com.example.libraryproject.controller.v1;

import com.example.libraryproject.repository.BookRepository;
import com.example.libraryproject.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/book")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BookController {
   final BookService bookService;

}
