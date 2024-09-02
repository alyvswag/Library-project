package com.example.libraryproject.model.dao;

import com.example.libraryproject.model.dao.entity.Base;
import com.example.libraryproject.model.enums.base.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends Base {
    @Column(name = "name")
    String name;
    @Column(name = "surname")
    String surname;
    @Column(name = "email", unique = true)
    String email;
    @Column(name = "password")
    String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role", // Cədvəl adı
            joinColumns = @JoinColumn(name = "user_id"), // Bu sinifin sütunu
            inverseJoinColumns = @JoinColumn(name = "role_id") // Əlaqəli sinifin sütunu
    )
    List<Role> roles;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status;
}
