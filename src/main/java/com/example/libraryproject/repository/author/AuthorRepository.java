package com.example.libraryproject.repository.author;

import com.example.libraryproject.model.dao.Author;
import com.example.libraryproject.model.dao.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    @Query("SELECT a FROM Author a WHERE a.id = :id AND a.isActive = true")
    Optional<Author> findAuthorById(@Param("id") long id);

    @Query(" SELECT a FROM Author a where a.isActive = true ")
    List<Author>  findAllAuthor();

    @Query("SELECT a FROM Author a WHERE lower(a.authorName) = lower(:name) AND a.isActive = true ")
    List<Author> findAuthorByName(@Param("name") String name);


}
