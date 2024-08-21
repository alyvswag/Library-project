package com.example.libraryproject.service;

import com.example.libraryproject.mapper.PublisherMapper;
import com.example.libraryproject.model.dao.Author;
import com.example.libraryproject.model.dao.Publisher;
import com.example.libraryproject.model.dto.request.create.PublisherRequestCreate;
import com.example.libraryproject.model.dto.request.update.AuthorRequestUpdate;
import com.example.libraryproject.model.dto.request.update.PublisherRequestUpdate;
import com.example.libraryproject.repository.PublisherRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PublisherService {
    final PublisherRepository publisherRepository;
    final PublisherMapper publisherMapper;

    public void addPublisher(PublisherRequestCreate publisherRequestCreate) {
        Publisher publisher = publisherMapper.toEntity(publisherRequestCreate);
        publisher.setIsActive(true);
        publisherRepository.save(publisher);
    }

    public void updatePublisher(Long id, PublisherRequestUpdate publisherRequest) {
        Publisher publisherEntity = publisherRepository.findPublisherById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found"));

        publisherEntity.setPublisherName(publisherRequest.getPublisherName() == null ? publisherEntity.getPublisherName() : publisherRequest.getPublisherName());
        publisherEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        publisherRepository.save(publisherEntity);
    }

    public void deletePublisher(Long id) {
        Publisher publisherEntity = publisherRepository.findPublisherById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found"));

        publisherEntity.setIsActive(false);
        publisherEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        publisherRepository.save(publisherEntity);

    }

    //

    public Publisher findById(Long id) {
        return id==null? null : publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found"));
    }

}
