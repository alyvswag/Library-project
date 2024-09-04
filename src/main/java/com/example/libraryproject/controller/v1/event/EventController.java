package com.example.libraryproject.controller.v1.event;

import com.example.libraryproject.model.dto.request.create.EventRequestCreate;
import com.example.libraryproject.model.dto.request.update.EventRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.EventResponseAdmin;
import com.example.libraryproject.model.dto.response.base.BaseResponse;
import com.example.libraryproject.service.event.EventService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class EventController {
    final EventService eventService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add-event")
    public BaseResponse<Void> addEvent(@RequestBody EventRequestCreate event) {
        eventService.addEvent(event);
        return BaseResponse.created();
    }
    @PutMapping("/update-event/{eventId}")
    public BaseResponse<Void> updateEvent(@PathVariable Long eventId, @RequestBody EventRequestUpdate event) {
        eventService.updateEvent(eventId, event);
        return BaseResponse.success();
    }
    @DeleteMapping("/delete-event/{eventId}")
    public BaseResponse<Void> removeEvent(@PathVariable Long eventId) {
        eventService.removeEvent(eventId);
        return BaseResponse.success();
    }
    @GetMapping("/get-all-events")
    public BaseResponse<List<EventResponseAdmin>> getAllEvents() {
        return BaseResponse.success(eventService.findAllEvent());
    }

}
