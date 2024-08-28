package com.example.libraryproject.mapper.user;

import com.example.libraryproject.model.dao.User;
import com.example.libraryproject.model.dto.request.create.AdminRequestCreate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "status" ,constant = "ACTIVE")
    User toEntity(AdminRequestCreate adminRequestCreate);
}
