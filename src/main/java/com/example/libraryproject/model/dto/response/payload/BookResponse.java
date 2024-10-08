package com.example.libraryproject.model.dto.response.payload;


import com.example.libraryproject.model.enums.book.Genre;
import com.example.libraryproject.model.enums.book.Language;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class BookResponse {
    //burda inheritance etmek olardi ama mapperde ignore istifade etmeyim deye istfade etmedim
    Long id;
    PublisherResponse publisher;
    AuthorResponse author;
    String bookName;
    BigDecimal price;
    String description;
    Genre genre;
    Language language;
    Integer pages;
    LocalDate publicationDate;
    Timestamp  createdAt;
    Timestamp  updatedAt;
}
