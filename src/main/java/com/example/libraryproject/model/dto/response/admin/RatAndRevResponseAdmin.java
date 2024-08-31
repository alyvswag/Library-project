package com.example.libraryproject.model.dto.response.admin;

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
public class RatAndRevResponseAdmin {
    Long id;
    BookResponseAdmin book;
    UserResponseAdmin user;
    Integer rating;
    String review;
    Timestamp createdAt;
    Timestamp  updatedAt;
}
