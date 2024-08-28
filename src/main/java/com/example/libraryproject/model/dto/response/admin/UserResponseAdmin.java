package com.example.libraryproject.model.dto.response.admin;

import com.example.libraryproject.model.enums.user.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseAdmin {
    Long id;
    String name;
    String surname;
    String email;
    Status status;
    Timestamp created_at;
    Timestamp updated_at;
}
