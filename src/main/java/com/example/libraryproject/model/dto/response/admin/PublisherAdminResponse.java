package com.example.libraryproject.model.dto.response.admin;

import com.example.libraryproject.model.dto.response.user.PublisherUserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class PublisherAdminResponse {
    Long id;
    String publisherName;
    Timestamp createdAt;
    Timestamp updatedAt;
}
