package com.example.libraryproject.mapper;

import com.example.libraryproject.model.dao.Author;
import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dao.Publisher;
import com.example.libraryproject.model.dto.request.create.BookRequestCreate;
import com.example.libraryproject.model.dto.request.update.BookRequestUpdate;
import com.example.libraryproject.model.dto.response.BookResponse;
import com.example.libraryproject.repository.AuhtorRepository;
import com.example.libraryproject.repository.PublisherRepository;
import com.example.libraryproject.service.AuthorService;
import com.example.libraryproject.service.PublisherService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring" , uses = {AuthorService.class, PublisherService.class})
public interface BookMapper {

    @Mapping(target = "author", source = "authorId")
    @Mapping(target = "publisher", source = "publisherId")
    Book toEntity(BookRequestCreate bookRequestCreate);

    @Mapping(target = "author", source = "authorId")
    @Mapping(target = "publisher", source = "publisherId")
    Book toEntity(BookRequestUpdate bookRequestUpdate);

    default Author mapAuthor(Long authorId, @Context AuthorService authorService) {
        return authorService.findById(authorId);
    }

    default Publisher mapPublisher(Long publisherId, @Context PublisherService publisherService) {
        return publisherService.findById(publisherId);
    }

    BookResponse toResponse(Book book);

    @Mapping(target = "author", source = "authorId")
    @Mapping(target = "publisher", source = "publisherId")
    List<BookResponse> toResponse(List<Book> books);

}
