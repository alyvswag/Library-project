package com.example.libraryproject.service.user;

import com.example.libraryproject.model.dao.User;
import com.example.libraryproject.model.dto.request.create.AdminRequestCreate;
import com.example.libraryproject.model.dto.request.update.UserRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.UserResponseAdmin;
import com.example.libraryproject.model.enums.user.RoleName;

import java.util.List;

public interface UserService {
    void addUser(AdminRequestCreate adminRequestCreate, RoleName roleName);

    void updateUser(Long id, UserRequestUpdate userRequestUpdate);

    void deleteUser(Long id);

    UserResponseAdmin getUserById(Long id);

    List<UserResponseAdmin> getAllUsers();

    UserResponseAdmin getUserByEmail(String email);

    List<UserResponseAdmin> getUsersByRole(RoleName roleName);

    void deactivateUser(Long id);

    User findById(Long id);
}
