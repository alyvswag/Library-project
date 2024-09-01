package com.example.libraryproject.model.dao;

import com.example.libraryproject.model.dao.entity.Base;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Author extends Base {
    @Column(name = "name")
    String authorName;
    @Column(name = "surname")
    String authorSurname;
    @Column(name = "description")
    String description;
    @OneToMany(
            mappedBy = "author",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    List<Book> books;
    @Column(name = "is_active")
    Boolean isActive;

    //one - author many kitab
    // many kitab one author
}
