package com.example.libraryproject.repository.user;

import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dao.User;
import com.example.libraryproject.model.dao.UserRole;
import com.example.libraryproject.model.enums.user.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = :email AND u.status = 'ACTIVE' ")
    Optional<User> findUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.id = :id AND u.status = 'ACTIVE' ")
    Optional<User> findUserById(@Param("id") Long id);

    @Query(" SELECT u FROM User u where u.status = 'ACTIVE' ")
    List<User> findAllUser();

    @Query("SELECT u FROM UserRole u WHERE u.role.roleName = :roleName AND u.isActive = true ")
    List<UserRole>  getUsersByRole(@Param("roleName") RoleName roleName);
}
