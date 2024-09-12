package com.example.libraryproject.service.report;

import com.example.libraryproject.mapper.book.BookMapper;
import com.example.libraryproject.mapper.rental.RentalMapper;
import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dto.response.payload.BookResponse;
import com.example.libraryproject.model.dto.response.payload.RentalResponse;
import com.example.libraryproject.repository.rental.RentalRepository;
import com.example.libraryproject.service.redis.RedisService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReportServiceImpl implements ReportService {

    final RentalRepository rentalRepository;
    final BookMapper bookMapper;
    final RentalMapper rentalMapper;
    final RedisService redisService;

    @Override
    public Map<BookResponse, Long> generateRentalStatistics(LocalDate startDate, LocalDate endDate) {
        List<Object[]> queryResults = rentalRepository.findRentalStatisticsWithCounts(startDate, endDate);
        Map<BookResponse, Long> statistics = new HashMap<>();
        for (Object[] result : queryResults) {
            statistics.put(bookMapper.toResponse((Book) result[0]), (Long) result[1]);
        }
        return statistics;
    }

    @Override
    public Map<Integer, BookResponse> getMostReadBooks() {
        List<Book> bookEntities = rentalRepository.findBooksMostRead(PageRequest.of(0, 5));
        Map<Integer, BookResponse> mostReadBooks = new HashMap<>();
        int i = 1;
        for (Book book : bookEntities) {
            mostReadBooks.put(i++, bookMapper.toResponse(book));
        }
        return mostReadBooks;
    }

    @Override
    public List<RentalResponse> generateUserActivityReport(Long userId) {
        return rentalMapper.toDtoUserActivity(rentalRepository.findRentalByUserId(userId));
    }

    @Override
    public List<RentalResponse> getBookRentalHistory(Long bookId) {
        return rentalMapper.toDtoBookRentalHistory(rentalRepository.findRentalByBookId(bookId));
    }

    @Override
    public List<String> getUserLoginHistory(String email) {
        return redisService.getUserLoginHistory(email);
    }

}
