package com.example.libraryproject.service.publisher;

import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.mapper.publisher.PublisherMapper;
import com.example.libraryproject.model.dao.Publisher;
import com.example.libraryproject.model.dto.request.create.PublisherRequestCreate;
import com.example.libraryproject.model.dto.request.update.PublisherRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.PublisherResponseAdmin;
import com.example.libraryproject.repository.publisher.PublisherRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

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
                .orElseThrow(() -> BaseException.notFound(Publisher.class.getSimpleName(), "publisher", String.valueOf(id)));

        publisherMapper.updatePublisherFromDto(publisherRequest, publisherEntity);
        publisherRepository.save(publisherEntity);
    }

    public void deletePublisher(Long id) {
        Publisher publisherEntity = publisherRepository.findPublisherById(id)
                .orElseThrow(() -> BaseException.notFound(Publisher.class.getSimpleName(), "publisher", String.valueOf(id)));

        publisherEntity.setIsActive(false);
        publisherEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        publisherRepository.save(publisherEntity);

    }

    public List<PublisherResponseAdmin> getAllPublishers() {
        return publisherMapper.toResponse(publisherRepository.findAllPublisher());
    }

    //

    public Publisher findById(Long id) {
        return id == null ? null : publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found"));
    }


}
