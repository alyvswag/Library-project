package com.example.libraryproject.service.report;

import com.example.libraryproject.model.dto.response.admin.BookResponseAdmin;
import com.example.libraryproject.model.dto.response.admin.RentalResponseAdmin;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface ReportService {
    Map<Integer, BookResponseAdmin> getMostReadBooks();

    Map<BookResponseAdmin, Long> generateRentalStatistics(LocalDate startDate, LocalDate endDate);

    List<RentalResponseAdmin> generateUserActivityReport(Long userId);

    List<RentalResponseAdmin> getBookRentalHistory(Long bookId);

    List<String> getUserLoginHistory(String email);
}
