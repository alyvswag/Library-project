package com.example.libraryproject.model.dao;

import com.example.libraryproject.model.dao.entity.Base;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ratings_and_reviews")
public class RatingAndReview extends Base {
    @JoinColumn(name = "book_id" ,referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    Book book;
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    User user;
    @Column(name = "rating")
    Integer rating;
    @Column(name = "review")
    String review;
    @Column(name = "is_active")
    Boolean isActive;
}
