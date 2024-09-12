package com.example.libraryproject.service.user;

import com.example.libraryproject.model.dao.User;
import com.example.libraryproject.model.dto.request.create.AdminRequestCreate;
import com.example.libraryproject.model.dto.request.update.UserRequestUpdate;
import com.example.libraryproject.model.dto.response.payload.UserResponse;
import com.example.libraryproject.model.enums.user.RoleName;

import java.util.List;

public interface UserService {
    void addUser(AdminRequestCreate adminRequestCreate, RoleName roleName);

    void updateUser(Long id, UserRequestUpdate userRequestUpdate);

    void deleteUser(Long id);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();

    UserResponse getUserByEmail(String email);

    List<UserResponse> getUsersByRole(RoleName roleName);

    void deactivateUser(Long id);

    User findById(Long id);
}
