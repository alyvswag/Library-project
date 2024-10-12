package com.example.libraryproject.repository.user;

import com.example.libraryproject.model.dao.entity.Role;
import com.example.libraryproject.model.enums.user.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

   @Query("select r from Role r where r.roleName = :roleName ")
    Role findRole(@Param("roleName") RoleName roleName);
}
