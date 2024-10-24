package com.example.libraryproject.service.publisher;

import com.example.libraryproject.model.dao.entity.Publisher;
import com.example.libraryproject.model.dto.request.create.PublisherRequestCreate;
import com.example.libraryproject.model.dto.request.update.PublisherRequestUpdate;
import com.example.libraryproject.model.dto.response.payload.PublisherResponse;

import java.util.List;

public interface PublisherService {
    void addPublisher(PublisherRequestCreate publisherRequestCreate);

    void updatePublisher(Long id, PublisherRequestUpdate publisherRequest);

    void deletePublisher(Long id);

    List<PublisherResponse> getAllPublishers();

    Publisher findById(Long id);
}
