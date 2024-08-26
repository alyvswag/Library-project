package com.example.libraryproject.controller.v1.author;


import com.example.libraryproject.model.dto.response.admin.BookResponseAdmin;
import com.example.libraryproject.model.dto.response.base.BaseResponse;
import com.example.libraryproject.service.author.AuthorManagementService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/author")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorManagementController {
    final AuthorManagementService authorManagementService;

    @DeleteMapping("/removeBookFromAuthor")
    public BaseResponse<Void> removeBookFromAuthor(@RequestParam("bookId") Long bookId) {
        authorManagementService.removeBookFromAuthor(bookId);
        return BaseResponse.success();
    }

    @GetMapping("/getBooksByAuthor")
    public BaseResponse<List<BookResponseAdmin>> getBooksByAuthor(@RequestParam("authorId") Long authorId) {
        return BaseResponse.success(authorManagementService.getBooksByAuthorId(authorId));
    }
}
