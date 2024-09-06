package com.example.libraryproject.security.models.dto;

import com.example.libraryproject.model.dao.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshTokenDto {
    boolean rememberMe;
    User user;
}
