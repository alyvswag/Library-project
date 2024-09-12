package com.example.libraryproject;

import com.example.libraryproject.mapper.publisher.PublisherMapper;
import com.example.libraryproject.model.dao.Publisher;
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
        // Mock giriş verilənləri
        PublisherRequestCreate requestCreate = new PublisherRequestCreate();
        Publisher publisher = new Publisher();

        // Mock davranış
        when(publisherMapper.toEntity(requestCreate)).thenReturn(publisher);

        // Test metodun çağırılması
        publisherService.addPublisher(requestCreate);

        // Doğrulamalar
        verify(publisherMapper, times(1)).toEntity(requestCreate);
        verify(publisherRepository, times(1)).save(publisher);
    }

    @Test
    void testUpdatePublisher() {
        // Mock giriş verilənləri
        Long id = 1L;
        PublisherRequestUpdate requestUpdate = new PublisherRequestUpdate();
        Publisher publisher = new Publisher();

        // Mock davranış
        when(publisherRepository.findPublisherById(id)).thenReturn(Optional.of(publisher));

        // Test metodun çağırılması
        publisherService.updatePublisher(id, requestUpdate);

        // Doğrulamalar
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

        // Mock davranış
        when(publisherRepository.findPublisherById(id)).thenReturn(Optional.of(publisher));

        // Test metodun çağırılması
        publisherService.deletePublisher(id);

        // Doğrulamalar
        assertFalse(publisher.getIsActive()); // İstifadəçi deactiv olub
        verify(publisherRepository, times(1)).save(publisher);
    }

    @Test
    void testGetAllPublishers() {
        // Mock verilənlər
        List<Publisher> publishers = List.of(new Publisher());
        List<PublisherResponse> response = List.of(new PublisherResponse());

        // Mock davranış
        when(publisherRepository.findAllPublisher()).thenReturn(publishers);
        when(publisherMapper.toResponse(publishers)).thenReturn(response);

        // Test metodun çağırılması
        List<PublisherResponse> result = publisherService.getAllPublishers();

        // Doğrulamalar
        assertEquals(response, result);
        verify(publisherRepository, times(1)).findAllPublisher();
    }

    @Test
    void testFindById() {
        // Mock giriş verilənləri
        Long id = 1L;
        Publisher publisher = new Publisher();

        // Mock davranış
        when(publisherRepository.findById(id)).thenReturn(Optional.of(publisher));

        // Test metodun çağırılması
        Publisher result = publisherService.findById(id);

        // Doğrulamalar
        assertEquals(publisher, result);
        verify(publisherRepository, times(1)).findById(id);
    }

}

