package com.example.libraryproject.model.dao.entity;


import com.example.libraryproject.model.dao.Base;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
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
    @Column(name ="reserved_quantity" ) //,  columnDefinition = "integer default 0"
    Integer reservedQuantity;
    @Column(name ="rental_quantity" )
    Integer rentalQuantity;

    @Override
    public String toString() {
        return "QuantityBook{" +
                ", quantity=" + quantity +
                ", reservedQuantity=" + reservedQuantity +
                ", rentalQuantity=" + rentalQuantity +
                '}';
    }
}
