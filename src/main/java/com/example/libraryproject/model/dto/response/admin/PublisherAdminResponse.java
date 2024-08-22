package com.example.libraryproject.model.dto.response.admin;

import com.example.libraryproject.model.dto.response.user.PublisherUserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class PublisherAdminResponse extends PublisherUserResponse {
    Long id;
    Timestamp createdAt;
    Timestamp updatedAt;
}
