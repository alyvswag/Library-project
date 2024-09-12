package com.example.libraryproject.mapper.publisher;

import com.example.libraryproject.model.dao.Publisher;
import com.example.libraryproject.model.dto.request.create.PublisherRequestCreate;
import com.example.libraryproject.model.dto.request.update.PublisherRequestUpdate;
import com.example.libraryproject.model.dto.response.payload.PublisherResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring" , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PublisherMapper {
    @Mapping(target = "isActive" ,constant = "true")
    Publisher toEntity(PublisherRequestCreate publisher);

    Publisher toEntity(PublisherRequestUpdate publisher);

    List<PublisherResponse> toResponse(List<Publisher> publishers);

    void updatePublisherFromDto(PublisherRequestUpdate dto, @MappingTarget Publisher publisher);
}
