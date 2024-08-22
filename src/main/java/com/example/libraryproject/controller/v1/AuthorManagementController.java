package com.example.libraryproject.controller.v1;


import com.example.libraryproject.model.dto.response.admin.BookAdminResponse;
import com.example.libraryproject.service.AuthorManagementService;
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
    public void removeBookFromAuthor(@RequestParam("bookId") Long bookId) {
        authorManagementService.removeBookFromAuthor(bookId);
    }

    @GetMapping("/getBooksByAuthor")
    public List<BookAdminResponse> getBooksByAuthor(@RequestParam("authorId") Long authorId) {
        return authorManagementService.getBooksByAuthorId(authorId);
    }
}
