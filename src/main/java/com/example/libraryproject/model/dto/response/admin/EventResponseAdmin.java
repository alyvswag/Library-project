package com.example.libraryproject.model.dto.response.admin;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class EventResponseAdmin {
    Long id;
    String eventName;
    LocalDate eventDate;
    String location;
    Boolean isActive;
    Timestamp createdAt;
    Timestamp  updatedAt;
}
