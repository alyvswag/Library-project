package com.example.libraryproject.model.dto.response.admin;


import com.example.libraryproject.enums.book.Genre;
import com.example.libraryproject.enums.book.Language;
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
public class BookAdminResponse  {
    //burda inheritance etmek olardi ama mapperde ignore istifade etmeyim deye istfade etmedim
    Long id;
    PublisherAdminResponse publisher;
    AuthorAdminResponse author;
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
