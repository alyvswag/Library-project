package com.example.libraryproject.model.dto.request.create;

import com.example.libraryproject.model.enums.notification.DataType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequestCreate {
    Long userId;
    DataType dataType;
    Long dataId;
    String message;
}
