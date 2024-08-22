package com.example.libraryproject.model.dto.response.admin;


import com.example.libraryproject.model.dto.response.user.BookUserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class BookAdminResponse extends BookUserResponse {
    Long id;
    Timestamp  createdAt;
    Timestamp  updatedAt;
}
