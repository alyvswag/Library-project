package com.example.libraryproject.mapper.user;

import com.example.libraryproject.model.dao.User;
import com.example.libraryproject.model.dto.request.create.AdminRequestCreate;
import com.example.libraryproject.model.dto.request.create.UserRequestCreate;
import com.example.libraryproject.model.dto.request.update.UserRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.UserResponseAdmin;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    @Mapping(target = "status" ,constant = "ACTIVE")
    User toEntity(AdminRequestCreate adminRequestCreate);

    void updateUserFromDto(UserRequestUpdate dto, @MappingTarget User user);

    UserResponseAdmin toDto(User user);

    List<UserResponseAdmin> toDto(List<User> users);

    @Mapping(target = "status" ,constant = "ACTIVE")
    User toEntity(UserRequestCreate userRequestCreate);
}
