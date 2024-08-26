package com.example.libraryproject.model.dao;

import com.example.libraryproject.model.dao.entity.Base;
import com.example.libraryproject.model.enums.user.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends Base {
    @Column(name = "name")
    String name;
    @Column(name = "surname")
    String surname;
    @Column(name = "email", unique = true)
    String email;
    @Column(name = "password")
    transient String password;
    @Column(name = "login_time")
    Timestamp loginTime;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status;
}
