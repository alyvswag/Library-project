package com.example.libraryproject.model.dto.request.create;


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
public class BookRequestCreate {
    Long publisherId;
    Long  authorId;
    String name;
    BigDecimal price;
    String description;
    // todo: genre request geldikde string gelecek sen ona diqqet ele
    Genre genre;
    Language language;
    Integer pages;
    LocalDate publicationDate;
    Integer quantity;
}
