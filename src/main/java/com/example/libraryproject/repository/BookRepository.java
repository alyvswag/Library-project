package com.example.libraryproject.repository;

import com.example.libraryproject.model.dao.Author;
import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dao.QuantityBook;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public  interface BookRepository  extends JpaRepository<Book, Long>  {
    @Query("SELECT b FROM Book b WHERE b.id = :id AND b.isActive = true")
    Optional<Book> findBookById(@Param("id") long id);

    @Query(" SELECT b FROM Book b where b.isActive = true ")
    List<Book> findAllBook();

    @Query("SELECT b FROM Book b WHERE b.isActive = true AND ( b.bookName LIKE %:searchWords% or b.author.authorName LIKE %:searchWords%) ")
    List<Book> searchBook(@Param("searchWords") String searchWords);


}
