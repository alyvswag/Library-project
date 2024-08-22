package com.example.libraryproject.model.dto.response.admin;


import com.example.libraryproject.model.dto.response.user.AuthorUserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class AuthorAdminResponse {
    Long id;
    String authorName;
    String authorSurname;
    String description;
    Timestamp createdAt;
    Timestamp updatedAt;
}
