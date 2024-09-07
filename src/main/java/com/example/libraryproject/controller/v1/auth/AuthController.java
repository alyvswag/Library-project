package com.example.libraryproject.controller.v1.auth;

import com.example.libraryproject.model.dto.response.base.BaseResponse;
import com.example.libraryproject.security.models.login.LoginRequestPayload;
import com.example.libraryproject.security.models.login.RefreshTokenRequestPayload;
import com.example.libraryproject.security.models.repsonse.LoginResponse;
import com.example.libraryproject.service.auth.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
    @PostMapping("/refreshToken")
    public BaseResponse<LoginResponse> refreshToken(@RequestBody RefreshTokenRequestPayload refreshToken) {
        return BaseResponse.success(authService.refreshToken(refreshToken));
    }
}
