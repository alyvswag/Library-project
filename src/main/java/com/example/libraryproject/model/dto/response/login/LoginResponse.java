package com.example.libraryproject.model.dto.response.login;

import com.example.libraryproject.model.dto.response.admin.UserResponseAdmin;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginResponse {
    String accessToken;
    String refreshToken;
    UserResponseAdmin user;
}
