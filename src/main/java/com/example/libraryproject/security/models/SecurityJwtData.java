package com.example.libraryproject.security.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SecurityJwtData {

    String publicKey;
    String privateKey;
    Long accessTokenValidityTime;
    Long  refreshTokenValidityTime;

}
