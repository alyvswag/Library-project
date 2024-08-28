package com.example.libraryproject.controller.v1.user;

import com.example.libraryproject.model.dto.request.create.AdminRequestCreate;
import com.example.libraryproject.model.dto.response.base.BaseResponse;
import com.example.libraryproject.service.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserController {
   final  UserService userService;

    @PostMapping
    public BaseResponse<Void> createUser(@RequestBody AdminRequestCreate adminRequestCreate) {
        userService.addUser(adminRequestCreate);
        return BaseResponse.success();
    }
}
