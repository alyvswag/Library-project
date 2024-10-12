package com.example.libraryproject.repository.publisher;

import com.example.libraryproject.model.dao.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    @Query("SELECT p FROM Publisher p WHERE p.id = :id AND p.isActive = true")
    Optional<Publisher> findPublisherById(@Param("id") long id);

    @Query(" SELECT p FROM Publisher p where p.isActive = true ")
    List<Publisher> findAllPublisher();

}
