package com.example.libraryproject.service;


import com.example.libraryproject.mapper.BookMapper;
import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dto.request.filter.BookRequestFilter;
import com.example.libraryproject.model.dto.response.user.BookUserResponse;
import com.example.libraryproject.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BookManagementService {
    final BookMapper bookMapper;
    final EntityManager em ;

    public List<BookUserResponse> filterBooks(BookRequestFilter bookRequest){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Book> root = query.from(Book.class);
        BigDecimal decimal = BigDecimal.valueOf(0);
        if(bookRequest.getMinPrice() !=null ){
            predicates.add(cb.greaterThanOrEqualTo(root.get("price"), bookRequest.getMinPrice()));
        }
        if( bookRequest.getMaxPrice() != null ){
            predicates.add(cb.lessThanOrEqualTo(root.get("price"), bookRequest.getMaxPrice()));
        }
        if(bookRequest.getGenre() !=null ){
            predicates.add(cb.like(root.get("genre"),"%"+ bookRequest.getGenre() + "%"));
        }
        if(bookRequest.getLanguage() != null){
            predicates.add(cb.like(root.get("language"),"%"+  bookRequest.getLanguage() + "%"));
        }

        if(bookRequest.getMinPages() != null ){
            predicates.add(cb.greaterThanOrEqualTo(root.get("pages"), bookRequest.getMinPages()));
        }
        if(bookRequest.getMaxPages() != null ){
            predicates.add(cb.lessThanOrEqualTo(root.get("pages"), bookRequest.getMaxPages()));
        }

        query.where(
                cb.and(predicates.toArray(new Predicate[0]))
        );
        TypedQuery<Book> typedQuery = em.createQuery(query);
      return  bookMapper.toResponseUser(typedQuery.getResultList());

    }
}
