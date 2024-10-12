package com.example.libraryproject.model.dao.entity;

import com.example.libraryproject.model.dao.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events")
public class Event extends Base {
    @Column(name = "event_name")
    String eventName;
    @Column(name = "event_date")
    LocalDate eventDate;
    @Column(name = "location")
    String location;
    @Column(name = "is_active")
    Boolean isActive;
}
