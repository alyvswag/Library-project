package com.example.libraryproject.model.dao;

import com.example.libraryproject.model.dao.entity.Base;
import com.example.libraryproject.model.enums.reservation.RezervStatus;
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
@Table(name = "reservations")
public class Reservation extends Base {
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    Book book;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    User user;
    @Column(name = "start_date")
    LocalDate startDate;
    @Column(name = "end_date")
    LocalDate endDate;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    RezervStatus status;
}
