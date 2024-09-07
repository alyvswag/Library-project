package com.example.libraryproject.service.auth;

import com.example.libraryproject.model.dto.request.create.UserRequestCreate;
import com.example.libraryproject.security.models.login.LoginRequestPayload;
import com.example.libraryproject.security.models.login.RefreshTokenRequestPayload;
import com.example.libraryproject.security.models.repsonse.LoginResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


public interface AuthService {
    //    void registerUser(UserRequestCreate userRequestCreate);
    LoginResponse login(LoginRequestPayload loginRequestPayload);

    LoginResponse refreshToken(RefreshTokenRequestPayload refreshToken);

    void logout();

    void setAuthentication(String email);

    void registerUser(UserRequestCreate userRequestCreate);
}
