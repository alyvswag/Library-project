package com.example.libraryproject.model.dto.request.update;


import com.example.libraryproject.enums.Genre;
import com.example.libraryproject.enums.Language;
import com.example.libraryproject.model.dao.Author;
import com.example.libraryproject.model.dao.Publisher;
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
public class BookRequestUpdate {
    Publisher publisher;
    Author author;
    String name;
    BigDecimal price;
    String description;
    Genre genre;
    Language language;
    Integer pages;
    LocalDate publicationDate;
}
