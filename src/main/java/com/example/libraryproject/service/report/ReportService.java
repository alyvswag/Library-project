package com.example.libraryproject.service.report;

import com.example.libraryproject.model.dto.response.payload.BookResponse;
import com.example.libraryproject.model.dto.response.payload.RentalResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface ReportService {
    Map<Integer, BookResponse> getMostReadBooks();

    Map<BookResponse, Long> generateRentalStatistics(LocalDate startDate, LocalDate endDate);

    List<RentalResponse> generateUserActivityReport(Long userId);

    List<RentalResponse> getBookRentalHistory(Long bookId);

    List<String> getUserLoginHistory(String email);
}
