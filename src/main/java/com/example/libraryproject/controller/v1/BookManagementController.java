package com.example.libraryproject.controller.v1;

import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dto.request.filter.BookRequestFilter;
import com.example.libraryproject.model.dto.response.user.BookUserResponse;
import com.example.libraryproject.service.BookManagementService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BookManagementController {
    final BookManagementService bookManagementService;

    @PostMapping("/filter")
    public List<BookUserResponse> filterBooks(@RequestBody BookRequestFilter bookRequest) {
        return bookManagementService.filterBooks(bookRequest);
    }


}
