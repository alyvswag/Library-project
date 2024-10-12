package com.example.libraryproject;

import com.example.libraryproject.mapper.publisher.PublisherMapper;
import com.example.libraryproject.model.dao.entity.Publisher;
import com.example.libraryproject.model.dto.request.create.PublisherRequestCreate;
import com.example.libraryproject.model.dto.request.update.PublisherRequestUpdate;
import com.example.libraryproject.model.dto.response.payload.PublisherResponse;
import com.example.libraryproject.repository.publisher.PublisherRepository;
import com.example.libraryproject.service.publisher.PublisherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PublisherServiceTest {

    @Mock
    private PublisherRepository publisherRepository;

    @Mock
    private PublisherMapper publisherMapper;

    @InjectMocks
    private PublisherServiceImpl publisherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Mock obyektləri yaratmaq üçün
    }

    @Test
    void testAddPublisher() {
        PublisherRequestCreate requestCreate = new PublisherRequestCreate();
        Publisher publisher = new Publisher();
        when(publisherMapper.toEntity(requestCreate)).thenReturn(publisher);
        publisherService.addPublisher(requestCreate);
        verify(publisherMapper, times(1)).toEntity(requestCreate);
        verify(publisherRepository, times(1)).save(publisher);
    }

    @Test
    void testUpdatePublisher() {
        Long id = 1L;
        PublisherRequestUpdate requestUpdate = new PublisherRequestUpdate();
        Publisher publisher = new Publisher();
        when(publisherRepository.findPublisherById(id)).thenReturn(Optional.of(publisher));
        publisherService.updatePublisher(id, requestUpdate);
        verify(publisherRepository, times(1)).findPublisherById(id);
        verify(publisherMapper, times(1)).updatePublisherFromDto(requestUpdate, publisher);
        verify(publisherRepository, times(1)).save(publisher);
    }

    @Test
    void testDeletePublisher() {
        // Mock giriş verilənləri
        Long id = 1L;
        Publisher publisher = new Publisher();
        publisher.setIsActive(true);
        when(publisherRepository.findPublisherById(id)).thenReturn(Optional.of(publisher));
        publisherService.deletePublisher(id);
        assertFalse(publisher.getIsActive());
        verify(publisherRepository, times(1)).save(publisher);
    }

    @Test
    void testGetAllPublishers() {
        List<Publisher> publishers = List.of(new Publisher());
        List<PublisherResponse> response = List.of(new PublisherResponse());
        when(publisherRepository.findAllPublisher()).thenReturn(publishers);
        when(publisherMapper.toResponse(publishers)).thenReturn(response);
        List<PublisherResponse> result = publisherService.getAllPublishers();
        assertEquals(response, result);
        verify(publisherRepository, times(1)).findAllPublisher();
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Publisher publisher = new Publisher();
        when(publisherRepository.findById(id)).thenReturn(Optional.of(publisher));
        Publisher result = publisherService.findById(id);
        assertEquals(publisher, result);
        verify(publisherRepository, times(1)).findById(id);
    }

}

