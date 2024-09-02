package com.example.libraryproject.repository.book;

import com.example.libraryproject.model.dao.QuantityBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuantityBookRepository extends JpaRepository<QuantityBook, Integer> {

}
