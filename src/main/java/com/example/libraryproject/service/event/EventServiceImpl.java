package com.example.libraryproject.service.event;

import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.mapper.mapper.EventMapper;
import com.example.libraryproject.model.dao.Event;
import com.example.libraryproject.model.dto.request.create.EventRequestCreate;
import com.example.libraryproject.model.dto.request.update.EventRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.EventResponseAdmin;
import com.example.libraryproject.repository.event.EventRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class EventServiceImpl implements EventService {
    final EventRepository eventRepository;
    final EventMapper eventMapper;

    @Override
    public void addEvent(EventRequestCreate eventRequestCreate) {
        eventRepository.save(eventMapper.toEntity(eventRequestCreate));
    }

    @Override
    public void updateEvent(Long id, EventRequestUpdate eventRequestUpdate) {
        Event eventEntity = eventRepository.findEventById(id)
                .orElseThrow(() -> BaseException.notFound(Event.class.getSimpleName(), "event", String.valueOf(id)));
        eventMapper.updateEventFromDto(eventRequestUpdate, eventEntity);
        eventRepository.save(eventEntity);
    }

    @Override
    public void removeEvent(Long id) {
        Event eventEntity = eventRepository.findEventById(id)
                .orElseThrow(() -> BaseException.notFound(Event.class.getSimpleName(), "event", String.valueOf(id)));
        eventEntity.setIsActive(false);
        eventRepository.save(eventEntity);
    }

    @Override
    public List<EventResponseAdmin> findAllEvent() {
        return eventMapper.toDtoList(eventRepository.findAllEvent());
    }

}
