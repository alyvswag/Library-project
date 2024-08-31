package com.example.libraryproject.model.dto.request.create;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class RatAndRevRequestCreate {
    Long bookId;
    Long userId;
    Integer rating;
    String review;
}
