package com.example.libraryproject.model.dao.entity;

import com.example.libraryproject.model.dao.Base;
import com.example.libraryproject.model.enums.rental.RentalStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental extends Base {
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    Book book;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    User user;
    @Column(name = "rental_start_date")
    LocalDate rentalStartDate;
    @Column(name = "rental_end_date")
    LocalDate rentalEndDate;
    @Column(name = "rental_status")
    @Enumerated(EnumType.STRING)
    RentalStatus rentalStatus;
    @Column(name = "return_date")
    LocalDate returnDate;
}
