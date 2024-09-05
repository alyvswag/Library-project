package com.example.libraryproject.model.dto.response.admin;

import com.example.libraryproject.model.enums.notification.DataType;
import com.example.libraryproject.model.enums.notification.NotificationStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class NotificationResponseAdmin {
    Long id;
    UserResponseAdmin user;
    DataType dataType;
    String message;
    NotificationStatus notificationStatus;
    Timestamp createdAt;
    Timestamp  updatedAt;
}
