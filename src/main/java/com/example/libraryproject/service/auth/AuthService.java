package com.example.libraryproject.service.auth;

import com.example.libraryproject.model.dto.request.create.UserRequestCreate;
import com.example.libraryproject.security.models.login.LoginRequestPayload;

import com.example.libraryproject.security.models.response.LoginResponse;


public interface AuthService {
    //    void registerUser(UserRequestCreate userRequestCreate);
    LoginResponse login(LoginRequestPayload loginRequestPayload);

    LoginResponse refreshToken(String refreshToken);

    void logout();

    void setAuthentication(String email);

    LoginResponse registerUser(UserRequestCreate userRequestCreate);
}
