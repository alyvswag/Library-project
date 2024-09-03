package com.example.libraryproject.controller.v1.user;

import com.example.libraryproject.model.dto.request.create.AdminRequestCreate;
import com.example.libraryproject.model.dto.request.update.UserRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.UserResponseAdmin;
import com.example.libraryproject.model.dto.response.base.BaseResponse;
import com.example.libraryproject.model.enums.user.RoleName;
import com.example.libraryproject.service.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserController {
   final  UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add-user/{roleName}")
    public BaseResponse<Void> addUser(@RequestBody AdminRequestCreate adminRequestCreate,@PathVariable RoleName roleName) {
        userService.addUser(adminRequestCreate,roleName);
        return BaseResponse.created();
    }
    @PutMapping("/update-user/{id}")
    public BaseResponse<Void> updateUser(@PathVariable Long id, @RequestBody UserRequestUpdate userRequestUpdate) {
        userService.updateUser(id, userRequestUpdate);
        return BaseResponse.success();
    }
    @DeleteMapping("/delete-user/{id}")
    public BaseResponse<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return BaseResponse.success();
    }
    @GetMapping("/get-user-by-id/{id}")
    public BaseResponse<UserResponseAdmin> getUserById(@PathVariable Long id) {
        return BaseResponse.success(userService.getUserById(id));
    }
    @GetMapping("/get-all-users")
    public BaseResponse<List<UserResponseAdmin>> getAllUsers() {
        return BaseResponse.success(userService.getAllUsers());
    }
    @GetMapping("/get-user-by-email/{email}")
    public BaseResponse<UserResponseAdmin> getUserByEmail(@PathVariable String email) {
        return BaseResponse.success(userService.getUserByEmail(email));
    }
    @GetMapping("/get-users-by-role/{roleName}")
    public BaseResponse<List<UserResponseAdmin>> getUsersByRole(@PathVariable RoleName roleName) {
        return BaseResponse.success(userService.getUsersByRole(roleName));
    }
    @DeleteMapping("/deactivate-user/{id}")
    public BaseResponse<Void> deactivateUser(@PathVariable Long id) {
        userService.deactivateUser(id);
        return BaseResponse.success();
    }

}
