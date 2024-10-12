package com.example.libraryproject.model.dao.entity;


import com.example.libraryproject.model.dao.Base;
import com.example.libraryproject.model.enums.book.Genre;
import com.example.libraryproject.model.enums.book.Language;
import com.example.libraryproject.model.enums.base.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book extends Base {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    Publisher publisher;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    Author author;
    @Column(name = "name")
    String bookName;
    @Column(name = "price")
    BigDecimal price;
    @Column(name = "description")
    String description;
    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    Genre genre;
    @Enumerated(EnumType.STRING)
    @Column(name = "language")
    Language language;
    @Column(name = "pages")
    Integer pages;
    @Column(name = "publication_date")
    LocalDate publicationDate;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status;
    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    QuantityBook quantityBook;

}
