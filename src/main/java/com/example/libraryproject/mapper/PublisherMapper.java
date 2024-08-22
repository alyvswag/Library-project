package com.example.libraryproject.mapper;

import com.example.libraryproject.model.dao.Publisher;
import com.example.libraryproject.model.dto.request.create.PublisherRequestCreate;
import com.example.libraryproject.model.dto.request.update.PublisherRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.PublisherAdminResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
    Publisher toEntity(PublisherRequestCreate publisher);
    Publisher toEntity(PublisherRequestUpdate publisher);
    List<PublisherAdminResponse> toResponse(List<Publisher> publishers);
}
