package com.example.libraryproject.controller.v1.book;

import com.example.libraryproject.model.dto.request.filter.BookRequestFilter;
import com.example.libraryproject.model.dto.response.payload.BookResponse;
import com.example.libraryproject.model.dto.response.payload.QuantityBookResponse;
import com.example.libraryproject.model.dto.response.base.BaseResponse;
import com.example.libraryproject.service.book.BookManagementService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book-management")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BookManagementController {
    final BookManagementService bookManagementService;

    @GetMapping("/search-books/{searchWords}")
    public BaseResponse<List<BookResponse>> searchBooks(@PathVariable String searchWords) {
        return BaseResponse.success(bookManagementService.searchBooks(searchWords));
    }


    @PostMapping("/filter-books")
    public BaseResponse<List<BookResponse>> filterBooks(@RequestBody BookRequestFilter bookRequest) {
        return BaseResponse.success(bookManagementService.filterBooks(bookRequest));
    }

    @GetMapping("/get-book-inventory")
    public BaseResponse<List<QuantityBookResponse>> getBookInventory() {
        return BaseResponse.success(bookManagementService.getBookInventory());
    }


}
