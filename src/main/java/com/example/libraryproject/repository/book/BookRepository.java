package com.example.libraryproject.repository.book;

import com.example.libraryproject.model.dao.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.id = :id AND b.status != 'DELETED' and b.quantityBook.quantity > 0")
    Optional<Book> findBookById(@Param("id") long id);

    @Query("SELECT b FROM Book b WHERE b.id = :id AND b.status = 'ACTIVE' and b.quantityBook.quantity > 0")
    Optional<Book> findActiveBookById(@Param("id") long id);

    @Query(" SELECT b FROM Book b where b.status = 'ACTIVE' and b.quantityBook.quantity > 0 ")
    List<Book> findAllBook();

    @Query("SELECT b FROM Book b WHERE b.status = 'ACTIVE'" +
            "  AND b.quantityBook.quantity > 0 " +
            "AND (LOWER(b.bookName) LIKE LOWER(CONCAT('%', :searchWords, '%')) " +
            "OR LOWER(b.author.authorName) LIKE LOWER(CONCAT('%', :searchWords, '%')) " +
            "OR LOWER(b.author.authorSurname) LIKE LOWER(CONCAT('%', :searchWords, '%'))) ")
    List<Book> searchBook(@Param("searchWords") String searchWords);

    //todo : axtari tekce muellif adina gore yox soyadina gorede edilsin

    @Query("Select b from Book b where b.author.id = :authorId AND b.status = 'ACTIVE' ")
    Optional<List<Book>> findBooksByAuthor(Long authorId);

}
