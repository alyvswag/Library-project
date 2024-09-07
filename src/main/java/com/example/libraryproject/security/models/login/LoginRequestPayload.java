package com.example.libraryproject.security.models.login;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequestPayload {

    String email;
    String password;
    Boolean rememberMe;

}
