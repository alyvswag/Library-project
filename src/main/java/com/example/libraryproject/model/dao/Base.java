package com.example.libraryproject.model.dao;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Base {
    //id, created_at,updated_at
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Cascade(CascadeType.ALL)//siliencek
    Long id;
    @CreationTimestamp
    @Column(name = "created_at")
    Timestamp createdAt;
    @Column(name = "updated_at")
    Timestamp updatedAt;
}
