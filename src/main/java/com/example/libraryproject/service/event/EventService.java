package com.example.libraryproject.service.event;

import com.example.libraryproject.model.dto.request.create.EventRequestCreate;
import com.example.libraryproject.model.dto.request.update.EventRequestUpdate;
import com.example.libraryproject.model.dto.response.payload.EventResponse;

import java.util.List;

public interface EventService {
    void addEvent(EventRequestCreate eventRequestCreate);
    void updateEvent(Long id,EventRequestUpdate eventRequestUpdate);
    void removeEvent(Long id );
    List<EventResponse> findAllEvent();

}
