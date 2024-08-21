package com.example.libraryproject.controller.v1;

import com.example.libraryproject.model.dao.Author;
import com.example.libraryproject.model.dto.request.create.AuthorRequestCreate;
import com.example.libraryproject.model.dto.request.update.AuthorRequestUpdate;
import com.example.libraryproject.model.dto.response.AuthorResponse;
import com.example.libraryproject.service.AuthorService;
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
    public void createAuthor(@RequestBody AuthorRequestCreate author) {
        authorService.addAuthor(author);
    }
    @PutMapping("/update/{id}")
    public void updateAuthor(@PathVariable("id") Long id, @RequestBody AuthorRequestUpdate author) {
        authorService.updateAuthor(id, author);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
    }
    @GetMapping("/getAuthorById/{id}")
    public AuthorResponse getAuthorById(@PathVariable("id") Long id) {
        return authorService.getAuthorById(id);
    }
    @GetMapping("/getAllAuthors")
    public List<AuthorResponse> getAllAuthors() {
        return authorService.getAllAuthors();
    }
    @GetMapping("/getAuthorByName/{name}")
    public List<AuthorResponse> getAuthorByName(@PathVariable("name") String name) {
        return authorService.getAuthorByName(name);
    }
}
