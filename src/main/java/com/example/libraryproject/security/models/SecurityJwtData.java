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

    public Long getRefreshTokenValidityTime(boolean rememberMe) {
        return refreshTokenValidityTime * (rememberMe ? 30L : 1L);
    }

}
