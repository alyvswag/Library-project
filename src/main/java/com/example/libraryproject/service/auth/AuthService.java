package com.example.libraryproject.service.auth;

import com.example.libraryproject.model.dto.request.create.UserRequestCreate;
import com.example.libraryproject.model.dto.request.login.LoginRequestPayload;

import com.example.libraryproject.model.dto.response.login.LoginResponse;


public interface AuthService {
    //    void registerUser(UserRequestCreate userRequestCreate);
    LoginResponse login(LoginRequestPayload loginRequestPayload);

    LoginResponse refreshToken(String refreshToken);

    void logout();

    void setAuthentication(String email);

    LoginResponse registerUser(UserRequestCreate userRequestCreate);
}
