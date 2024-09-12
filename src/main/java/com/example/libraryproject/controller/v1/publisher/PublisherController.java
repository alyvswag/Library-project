package com.example.libraryproject.controller.v1.publisher;


import com.example.libraryproject.model.dto.request.create.PublisherRequestCreate;
import com.example.libraryproject.model.dto.request.update.PublisherRequestUpdate;
import com.example.libraryproject.model.dto.response.payload.PublisherResponse;
import com.example.libraryproject.model.dto.response.base.BaseResponse;
import com.example.libraryproject.service.publisher.PublisherService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publisher")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PublisherController {
    final PublisherService publisherService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public BaseResponse<Void> addPublisher(@RequestBody PublisherRequestCreate publisher) {
        publisherService.addPublisher(publisher);
        return BaseResponse.created();
    }

    @PutMapping("/update/{id}")
    public BaseResponse<Void> updatePublisher(@PathVariable Long id, @RequestBody PublisherRequestUpdate publisher) {
        publisherService.updatePublisher(id, publisher);
        return BaseResponse.success();
    }

    @DeleteMapping("/delete/{id}")
    public BaseResponse<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return BaseResponse.success();
    }

    @GetMapping("/get-all-publishers")
    public BaseResponse<List<PublisherResponse>> getAllPublishers() {
        return BaseResponse.success(publisherService.getAllPublishers());
    }

}
