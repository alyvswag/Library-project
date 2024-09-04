package com.example.libraryproject.repository.event;

import com.example.libraryproject.model.dao.Event;
import com.example.libraryproject.model.dao.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e WHERE e.id = :id AND e.isActive = true")
    Optional<Event> findEventById(@Param("id") Long id);

    @Query(" SELECT e FROM Event e ")
    List<Event> findAllEvent();

}
