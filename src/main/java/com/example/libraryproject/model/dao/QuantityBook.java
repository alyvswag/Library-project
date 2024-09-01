package com.example.libraryproject.model.dao;


import com.example.libraryproject.model.dao.entity.Base;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "quantity")
public class QuantityBook  extends Base {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    Book book;
    @Column(name ="quantity")
    Integer quantity;
}
