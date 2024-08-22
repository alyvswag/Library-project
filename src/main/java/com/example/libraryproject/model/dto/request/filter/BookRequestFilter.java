package com.example.libraryproject.model.dto.request.filter;

import com.example.libraryproject.enums.Genre;
import com.example.libraryproject.enums.Language;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestFilter {
    // qiymet bir araliqda gelecek
    BigDecimal minPrice;
    BigDecimal maxPrice;
    Genre genre;
    Language language;
    BigDecimal minPages;
    BigDecimal maxPages;
}
