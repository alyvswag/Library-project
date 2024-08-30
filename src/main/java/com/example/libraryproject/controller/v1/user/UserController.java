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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserController {
   final  UserService userService;

    @PostMapping("/addUser/{roleName}")
    public BaseResponse<Void> addUser(@RequestBody AdminRequestCreate adminRequestCreate,@PathVariable RoleName roleName) {
        userService.addUser(adminRequestCreate,roleName);
        return BaseResponse.success();
    }
    @PutMapping("/updateUser/{id}")
    public BaseResponse<Void> updateUser(@PathVariable Long id, @RequestBody UserRequestUpdate userRequestUpdate) {
        userService.updateUser(id, userRequestUpdate);
        return BaseResponse.success();
    }
    @DeleteMapping("/deleteUser/{id}")
    public BaseResponse<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return BaseResponse.success();
    }
    @GetMapping("/getUserById/{id}")
    public BaseResponse<UserResponseAdmin> getUserById(@PathVariable Long id) {
        return BaseResponse.success(userService.getUserById(id));
    }
    @GetMapping("/getAllUsers")
    public BaseResponse<List<UserResponseAdmin>> getAllUsers() {
        return BaseResponse.success(userService.getAllUsers());
    }
    @GetMapping("/getUserByEmail/{email}")
    public BaseResponse<UserResponseAdmin> getUserByEmail(@PathVariable String email) {
        return BaseResponse.success(userService.getUserByEmail(email));
    }
    @GetMapping("/getUsersByRole/{roleName}")
    public BaseResponse<List<UserResponseAdmin>> getUsersByRole(@PathVariable RoleName roleName) {
        return BaseResponse.success(userService.getUsersByRole(roleName));
    }
    @DeleteMapping("/deactivateUser/{id}")
    public BaseResponse<Void> deactivateUser(@PathVariable Long id) {
        userService.deactivateUser(id);
        return BaseResponse.success();
    }

}
