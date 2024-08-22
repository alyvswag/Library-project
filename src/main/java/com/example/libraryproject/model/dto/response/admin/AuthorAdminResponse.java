package com.example.libraryproject.model.dto.response.admin;


import com.example.libraryproject.model.dto.response.user.AuthorUserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class AuthorAdminResponse extends AuthorUserResponse {
    Long id;
    Timestamp createdAt;
    Timestamp updatedAt;
}
