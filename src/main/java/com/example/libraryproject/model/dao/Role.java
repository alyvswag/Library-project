package com.example.libraryproject.model.dao;

import com.example.libraryproject.model.dao.entity.Base;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends Base {
    @Column(name = "role_name")
    String roleName;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    List<User> users;
    @Column(name = "is_active")
    Boolean isActive;
}
