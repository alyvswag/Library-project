package com.example.libraryproject.repository.book;

import com.example.libraryproject.model.dao.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public  interface BookRepository  extends JpaRepository<Book, Long>  {
    @Query("SELECT b FROM Book b WHERE b.id = :id AND b.isActive = true and b.quantityBook.quantity > 0")
    Optional<Book> findBookById(@Param("id") long id);

    @Query(" SELECT b FROM Book b where b.isActive = true and b.quantityBook.quantity > 0 ")
    List<Book> findAllBook();

    @Query("SELECT b FROM Book b WHERE b.isActive = true AND b.quantityBook.quantity > 0 AND ( b.bookName LIKE %:searchWords% or b.author.authorName LIKE %:searchWords% or b.author.authorSurname LIKE %:searchWords%) ")
    List<Book> searchBook(@Param("searchWords") String searchWords);
    //todo : axtari tekce muellif adina gore yox soyadina gorede edilsin


}
