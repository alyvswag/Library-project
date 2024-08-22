package com.example.libraryproject.model.dto.response.admin;


import com.example.libraryproject.enums.Genre;
import com.example.libraryproject.enums.Language;
import com.example.libraryproject.model.dto.response.user.AuthorUserResponse;
import com.example.libraryproject.model.dto.response.user.BookUserResponse;
import com.example.libraryproject.model.dto.response.user.PublisherUserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class BookAdminResponse  {
    //burda inheritance etmek olardi ama mapperde ignore istifade etmeyim deye istfade etmedim
    Long id;
    PublisherUserResponse publisher;
    AuthorUserResponse author;
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
