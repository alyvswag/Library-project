package com.example.libraryproject.mapper.author;

import com.example.libraryproject.model.dao.Author;
import com.example.libraryproject.model.dto.request.create.AuthorRequestCreate;
import com.example.libraryproject.model.dto.request.update.AuthorRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.AuthorAdminResponse;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author  toEntity(AuthorRequestCreate author);
    Author  toEntity(AuthorRequestUpdate author);
    AuthorAdminResponse toResponse(Author author);
    List<AuthorAdminResponse> toResponse(List<Author> authors);
}
