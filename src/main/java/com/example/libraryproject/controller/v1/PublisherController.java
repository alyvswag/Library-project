package com.example.libraryproject.controller.v1;


import com.example.libraryproject.model.dao.Publisher;
import com.example.libraryproject.model.dto.request.create.PublisherRequestCreate;
import com.example.libraryproject.model.dto.request.update.PublisherRequestUpdate;
import com.example.libraryproject.service.PublisherService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/publisher")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PublisherController {
    final PublisherService publisherService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public void addPublisher(@RequestBody PublisherRequestCreate publisher) {
        publisherService.addPublisher(publisher);
    }

    @PutMapping("/update/{id}")
    public void updatePublisher(@PathVariable Long id, @RequestBody PublisherRequestUpdate publisher) {
        publisherService.updatePublisher(id,publisher);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
    }

}
