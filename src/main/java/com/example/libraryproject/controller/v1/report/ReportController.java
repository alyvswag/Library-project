package com.example.libraryproject.controller.v1.report;

import com.example.libraryproject.model.dto.response.admin.BookResponseAdmin;
import com.example.libraryproject.model.dto.response.admin.RentalResponseAdmin;
import com.example.libraryproject.model.dto.response.base.BaseResponse;
import com.example.libraryproject.service.report.ReportService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/report")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ReportController {
    final ReportService reportService;

    @GetMapping("/generate-rental-statistics/{startDate}/{endDate}")
    public BaseResponse<Map<BookResponseAdmin,Long>> generateRentalStatistics(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
        return BaseResponse.success(reportService.generateRentalStatistics(startDate,endDate));
    }

    @GetMapping("/get-most-read-books")
    public BaseResponse<Map<Integer,BookResponseAdmin>> getMostReadBooks() {
        return BaseResponse.success(reportService.getMostReadBooks());
    }
    @GetMapping("/generate-user-activity-report/{userId}")
    public BaseResponse<List<RentalResponseAdmin>> generateUserActivityReport(@PathVariable Long userId) {
        return BaseResponse.success(reportService.generateUserActivityReport(userId));
    }
    @GetMapping("/get-book-rental-history/{bookId}")
    public BaseResponse<List<RentalResponseAdmin>> getBookRentalHistory(@PathVariable Long bookId) {
        return BaseResponse.success(reportService.getBookRentalHistory(bookId));
    }
    @GetMapping("/get-user-login-history/{email}")
    public BaseResponse<List<String>> getUserLoginHistory(@PathVariable String email) {
        return BaseResponse.success(reportService.getUserLoginHistory(email));
    }

}
