package com.example.libraryproject.mapper.book;

import com.example.libraryproject.model.dao.Author;
import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dao.Publisher;
import com.example.libraryproject.model.dto.request.create.BookRequestCreate;
import com.example.libraryproject.model.dto.response.admin.BookResponseAdmin;
import com.example.libraryproject.model.dto.response.user.BookResponseUser;
import com.example.libraryproject.service.author.AuthorService;
import com.example.libraryproject.service.publisher.PublisherService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring" , uses = {AuthorService.class, PublisherService.class})
public interface BookMapper {

    @Mapping(target = "author", source = "authorId")
    @Mapping(target = "publisher", source = "publisherId")
    Book toEntity(BookRequestCreate bookRequestCreate);


    default Author mapAuthor(Long authorId, @Context AuthorService authorService) {
        return authorService.findById(authorId);
    }

    default Publisher mapPublisher(Long publisherId, @Context PublisherService publisherService) {
        return publisherService.findById(publisherId);
    }

    BookResponseAdmin toResponse(Book book);

    @Mapping(target = "author", source = "authorId")
    @Mapping(target = "publisher", source = "publisherId")
    List<BookResponseAdmin> toResponse(List<Book> books);

    @Mapping(target = "author", source = "authorId")
    @Mapping(target = "publisher", source = "publisherId")
    List<BookResponseUser> toResponseUser(List<Book> books);

}