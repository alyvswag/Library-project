package com.example.libraryproject.mapper.notification;


import com.example.libraryproject.model.dao.Notification;
import com.example.libraryproject.model.dao.User;
import com.example.libraryproject.model.dto.request.create.NotificationRequestCreate;
import com.example.libraryproject.model.dto.response.payload.NotificationResponse;
import com.example.libraryproject.service.user.UserService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserService.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NotificationMapper {
    @Mapping(target = "notificationStatus", constant = "SENT")
    @Mapping(target = "user", source = "userId")
    Notification toEntity(NotificationRequestCreate notificationRequestCreate);

    default User mapUser(Long userId, @Context UserService userService) {
        return userService.findById(userId);
    }

    NotificationResponse toDto(Notification notification);

    List<NotificationResponse> toDto(List<Notification> notifications);
}
