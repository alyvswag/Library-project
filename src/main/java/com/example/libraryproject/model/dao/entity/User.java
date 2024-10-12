package com.example.libraryproject.model.dao.entity;

import com.example.libraryproject.model.dao.Base;
import com.example.libraryproject.model.enums.base.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", status=" + status +
                '}';
    }
}
