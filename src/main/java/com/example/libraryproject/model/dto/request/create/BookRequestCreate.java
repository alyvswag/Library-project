package com.example.libraryproject.model.dto.request.create;


import com.example.libraryproject.model.enums.book.Genre;
import com.example.libraryproject.model.enums.book.Language;
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
public class BookRequestCreate {
    Long publisherId;
    Long  authorId;
    String bookName;
    BigDecimal price;
    String description;
    Genre genre;
    Language language;
    Integer pages;
    LocalDate publicationDate;
    Integer quantity;
}
