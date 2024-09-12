package com.example.libraryproject.model.dto.response.payload;

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
public class RatAndRevResponse {
    Long id;
    BookResponse book;
    UserResponse user;
    Integer rating;
    String review;
    Timestamp createdAt;
    Timestamp  updatedAt;
}
