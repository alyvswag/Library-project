package com.example.libraryproject.model.dao;


import com.example.libraryproject.enums.Genre;
import com.example.libraryproject.enums.Language;
import com.example.libraryproject.model.dao.entity.Base;
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
    String name;
    @Column(name = "price")
    BigDecimal price;
    @Column(name = "description")
    String description;
    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    Genre genre;
    @Column(name = "language")
    Language language;
    @Column(name = "paged")
    Integer pages;
    @Column(name = "publication_date")
    LocalDate publicationDate;
    @Column(name = "is_active")
    Boolean isActive;
}
