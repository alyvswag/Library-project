package com.example.libraryproject.model.dto.response;


import com.example.libraryproject.enums.Genre;
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
public class BookResponse {
    Publisher publisher;
    Author author;
    String name;
    BigDecimal price;
    String description;
    // todo: genre request geldikde string gelecek sen ona diqqet ele
    Genre genre;
    Integer pages;
    LocalDate publicationDate;
}
