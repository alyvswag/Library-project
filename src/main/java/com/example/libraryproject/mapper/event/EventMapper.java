package com.example.libraryproject.mapper.event;

import com.example.libraryproject.model.dao.entity.Event;
import com.example.libraryproject.model.dto.request.create.EventRequestCreate;
import com.example.libraryproject.model.dto.request.update.EventRequestUpdate;
import com.example.libraryproject.model.dto.response.payload.EventResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EventMapper {
    @Mapping(target = "isActive" ,constant = "true")
    Event  toEntity(EventRequestCreate eventRequestCreate);

    void updateEventFromDto(EventRequestUpdate dto, @MappingTarget Event event);

    List<EventResponse> toDtoList(List<Event> eventList);
}
