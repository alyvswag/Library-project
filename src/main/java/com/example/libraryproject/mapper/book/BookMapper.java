package com.example.libraryproject.mapper.book;

import com.example.libraryproject.model.dao.Author;
import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dao.Publisher;
import com.example.libraryproject.model.dto.request.create.BookRequestCreate;
import com.example.libraryproject.model.dto.request.update.BookRequestUpdate;
import com.example.libraryproject.model.dto.response.payload.BookResponse;
import com.example.libraryproject.service.author.AuthorService;
import com.example.libraryproject.service.publisher.PublisherService;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AuthorService.class, PublisherService.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookMapper {

    @Mapping(target = "author", source = "authorId")
    @Mapping(target = "publisher", source = "publisherId")
    @Mapping(target = "status", constant = "ACTIVE")
    Book toEntity(BookRequestCreate bookRequestCreate);

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


    void updateBookFromDto(BookRequestUpdate dto, @MappingTarget Book book);

}
