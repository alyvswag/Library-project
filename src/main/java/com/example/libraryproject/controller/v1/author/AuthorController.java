package com.example.libraryproject.controller.v1.author;

import com.example.libraryproject.model.dto.request.create.AuthorRequestCreate;
import com.example.libraryproject.model.dto.request.update.AuthorRequestUpdate;
import com.example.libraryproject.model.dto.response.payload.AuthorResponse;
import com.example.libraryproject.model.dto.response.base.BaseResponse;
import com.example.libraryproject.service.author.AuthorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/author")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorController {

    final AuthorService authorService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public BaseResponse<Void> createAuthor(@RequestBody AuthorRequestCreate author) {
        authorService.addAuthor(author);
        return BaseResponse.created();
    }
    @PutMapping("/update/{id}")
    public BaseResponse<Void> updateAuthor(@PathVariable("id") Long id, @RequestBody AuthorRequestUpdate author) {
        authorService.updateAuthor(id, author);
        return BaseResponse.success();
    }
    @DeleteMapping("/delete/{id}")
    public BaseResponse<Void> deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
        return BaseResponse.success();
    }
    @GetMapping("/get-author-by-id/{id}")
    public BaseResponse<AuthorResponse> getAuthorById(@PathVariable("id") Long id) {
        return BaseResponse.success(authorService.getAuthorById(id));
    }
    @GetMapping("/get-all-authors")
    public BaseResponse<List<AuthorResponse>> getAllAuthors() {
        return BaseResponse.success(authorService.getAllAuthors());
    }
    @GetMapping("/get-author-by-name/{name}")
    public BaseResponse<List<AuthorResponse>> getAuthorByName(@PathVariable("name") String name) {
        return BaseResponse.success(authorService.getAuthorByName(name));
    }

}
