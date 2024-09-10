package com.example.libraryproject.controller.v1.auth;

import com.example.libraryproject.model.dto.request.create.UserRequestCreate;
import com.example.libraryproject.model.dto.response.base.BaseResponse;
import com.example.libraryproject.model.dto.request.login.LoginRequestPayload;
import com.example.libraryproject.model.dto.response.login.LoginResponse;
import com.example.libraryproject.service.auth.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthController {
    final AuthService authService;

    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody LoginRequestPayload loginRequestPayload) {
        return BaseResponse.success(authService.login(loginRequestPayload));
    }

    @PostMapping("/refresh-token/{refreshToken}")
    public BaseResponse<LoginResponse> refreshToken(@PathVariable("refreshToken") String refreshToken) {
        return BaseResponse.success(authService.refreshToken(refreshToken));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register-user")
    public BaseResponse<LoginResponse> registerUser(@RequestBody UserRequestCreate userRequestCreate) {
        return BaseResponse.success(authService.registerUser(userRequestCreate));
    }

    @PostMapping("/logout")
    public BaseResponse<Void> logout() {
        authService.logout();
        return BaseResponse.success();
    }
}
