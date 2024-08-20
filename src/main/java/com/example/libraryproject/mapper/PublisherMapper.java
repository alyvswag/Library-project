package com.example.libraryproject.mapper;

import com.example.libraryproject.model.dao.Publisher;
import com.example.libraryproject.model.dto.request.create.PublisherRequestCreate;
import com.example.libraryproject.model.dto.request.update.PublisherRequestUpdate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
    Publisher toEntity(PublisherRequestCreate publisher);
    Publisher toEntity(PublisherRequestUpdate publisher);
}
