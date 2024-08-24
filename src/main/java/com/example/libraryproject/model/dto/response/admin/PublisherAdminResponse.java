package com.example.libraryproject.model.dto.response.admin;

import com.example.libraryproject.model.dto.response.user.PublisherUserResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublisherAdminResponse {
    Long id;
    String publisherName;
    Timestamp createdAt;
    Timestamp updatedAt;
}
