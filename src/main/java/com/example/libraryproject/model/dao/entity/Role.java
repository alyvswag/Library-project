package com.example.libraryproject.model.dao.entity;

import com.example.libraryproject.model.dao.Base;
import com.example.libraryproject.model.enums.user.RoleName;
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
@Table(name = "roles")
public class Role extends Base {
    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    RoleName roleName;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    List<User> users;
    @Column(name = "is_active")
    Boolean isActive;

    @Override
    public String toString() {
        return "Role{" +
                "roleName=" + roleName +
                ", isActive=" + isActive +
                '}';
    }
}
