package com.example.libraryproject.model.dto.response.user;


import com.example.libraryproject.model.enums.book.Genre;
import com.example.libraryproject.model.enums.book.Language;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponseUser {
    PublisherResponseUser publisher;
    AuthorResponseUser author;
    String bookName;
    BigDecimal price;
    String description;
    Genre genre;
    Language language;
    Integer pages;
    LocalDate publicationDate;
}
