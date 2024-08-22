package com.example.libraryproject.service;

import com.example.libraryproject.mapper.AuthorMapper;
import com.example.libraryproject.model.dao.Author;
import com.example.libraryproject.model.dto.request.create.AuthorRequestCreate;
import com.example.libraryproject.model.dto.request.update.AuthorRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.AuthorAdminResponse;
import com.example.libraryproject.model.dto.response.user.AuthorUserResponse;
import com.example.libraryproject.repository.AuthorRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorService {
    final AuthorMapper authorMapper;
    final AuthorRepository authorRepository;

    public void addAuthor(AuthorRequestCreate author) {
        Author authorEntity = authorMapper.toEntity(author);
        authorEntity.setIsActive(true);
        authorRepository.save(authorEntity);
    }

    public void updateAuthor(Long id , AuthorRequestUpdate authorRequest) {
        Author authorEntity = authorRepository.findAuthorById(id)
                .orElseThrow(() -> new RuntimeException("Author not found")); // todo: oz exception classin

        authorEntity.setName(authorRequest.getName() == null ? authorEntity.getName() : authorRequest.getName());
        authorEntity.setSurname(authorRequest.getSurname() == null ? authorEntity.getSurname() : authorRequest.getSurname());
        authorEntity.setDescription(authorRequest.getDescription() == null ? authorEntity.getDescription() : authorRequest.getDescription());
        authorEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        authorRepository.save(authorEntity);

    }

    public void deleteAuthor(Long id) {
        Author authorEntity = authorRepository.findAuthorById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        authorEntity.setIsActive(false);
        authorEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        authorRepository.save(authorEntity);

    }

    public AuthorAdminResponse getAuthorById(Long id) {
        Author authorEntity = authorRepository.findAuthorById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        //Todo : sexsi exception classlar

        return authorMapper.toResponse(authorEntity);

    }

    public List<AuthorAdminResponse> getAllAuthors(){
        return  authorMapper.toResponse(authorRepository.findAllAuthor());
    }

    public List<AuthorAdminResponse> getAuthorByName(String name) {
        List<Author> authorEntity = authorRepository.findAuthorByName(name);
        if(authorEntity.isEmpty()){
            throw new RuntimeException("Author not found");
        }
        //Todo : sexsi exception classlar

        return authorMapper.toResponse(authorEntity);

    }



    //
    public Author findById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }


}