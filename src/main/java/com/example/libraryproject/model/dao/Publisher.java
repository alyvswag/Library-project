package com.example.libraryproject.model.dao;

import com.example.libraryproject.model.dao.entity.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "publishers")
public class Publisher extends Base {
    @Column(name = "publisher_name")
    String publisherName;
    @Column(name = "is_active")
    Boolean isActive;
}
