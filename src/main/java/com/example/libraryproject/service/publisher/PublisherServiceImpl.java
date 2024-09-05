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

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PublisherServiceImpl implements PublisherService {

    final PublisherRepository publisherRepository;
    final PublisherMapper publisherMapper;

    @Override
    public void addPublisher(PublisherRequestCreate publisherRequestCreate) {
        Publisher publisher = publisherMapper.toEntity(publisherRequestCreate);
        publisherRepository.save(publisher);
    }

    @Override
    public void updatePublisher(Long id, PublisherRequestUpdate publisherRequest) {
        Publisher publisherEntity = findPublisherById(id);
        publisherMapper.updatePublisherFromDto(publisherRequest, publisherEntity);
        publisherRepository.save(publisherEntity);
    }

    @Override
    public void deletePublisher(Long id) {
        Publisher publisherEntity = findPublisherById(id);
        publisherEntity.setIsActive(false);
        publisherRepository.save(publisherEntity);

    }

    @Override
    public List<PublisherResponseAdmin> getAllPublishers() {
        return publisherMapper.toResponse(publisherRepository.findAllPublisher());
    }

    //public
    @Override
    public Publisher findById(Long id) {
        if (id == null) {
            throw BaseException.nullNotAllowed(Publisher.class.getSimpleName());
        }
        return publisherRepository.findById(id)
                .orElseThrow(() -> BaseException.notFound(Publisher.class.getSimpleName(), "publisher", String.valueOf(id)));
    }

    //private
    private Publisher findPublisherById(Long id) {
        return publisherRepository.findPublisherById(id)
                .orElseThrow(() -> BaseException.notFound(Publisher.class.getSimpleName(), "publisher", String.valueOf(id)));
    }


}
