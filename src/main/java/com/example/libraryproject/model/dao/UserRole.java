package com.example.libraryproject.model.dao;

import com.example.libraryproject.model.dao.entity.Base;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_role")
    public class UserRole extends Base {
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id"  ,referencedColumnName = "id")
        User user;
        @JoinColumn(name = "role_id"  ,referencedColumnName = "id")
        @ManyToOne(fetch = FetchType.LAZY)
        Role role;
        @Column(name ="is_active")
        Boolean isActive;
    }
