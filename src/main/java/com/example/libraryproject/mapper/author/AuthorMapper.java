package com.example.libraryproject.mapper.author;

import com.example.libraryproject.model.dao.Author;
import com.example.libraryproject.model.dto.request.create.AuthorRequestCreate;
import com.example.libraryproject.model.dto.request.update.AuthorRequestUpdate;
import com.example.libraryproject.model.dto.response.payload.AuthorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthorMapper {
    @Mapping(target = "isActive", constant = "true")
    Author  toEntity(AuthorRequestCreate author);

    Author  toEntity(AuthorRequestUpdate author);

    AuthorResponse toResponse(Author author);

    List<AuthorResponse> toResponse(List<Author> authors);

    void updateAuthorFromDto(AuthorRequestUpdate dto, @MappingTarget Author author);
}
