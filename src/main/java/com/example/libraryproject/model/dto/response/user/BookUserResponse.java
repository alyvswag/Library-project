package com.example.libraryproject.model.dto.response.user;


import com.example.libraryproject.enums.Genre;
import com.example.libraryproject.enums.Language;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class BookUserResponse {
    PublisherUserResponse publisher;
    AuthorUserResponse author;
    String bookName;
    BigDecimal price;
    String description;
    Genre genre;
    Language language;
    Integer pages;
    LocalDate publicationDate;
}
