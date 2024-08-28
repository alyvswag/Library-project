package com.example.libraryproject.model.dao;

import com.example.libraryproject.model.dao.entity.Base;
import com.example.libraryproject.model.enums.user.RoleName;
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
    @Enumerated(EnumType.STRING)
    RoleName roleName;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    List<User> users;
    @Column(name = "is_active")
    Boolean isActive;
}
