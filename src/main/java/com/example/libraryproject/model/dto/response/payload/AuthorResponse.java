package com.example.libraryproject.model.dto.response.payload;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorResponse {
    Long id;
    String authorName;
    String authorSurname;
    String description;
    Timestamp createdAt;
    Timestamp updatedAt;
}
