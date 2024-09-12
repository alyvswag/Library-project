package com.example.libraryproject.controller.v1.management;

import com.example.libraryproject.model.dto.response.payload.RentalResponse;
import com.example.libraryproject.model.dto.response.base.BaseResponse;
import com.example.libraryproject.model.enums.rental.RentalStatus;
import com.example.libraryproject.service.management.ManagementService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/management")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ManagementController {
    final ManagementService managementService;

    @GetMapping("/check-overdue-books")
    public BaseResponse<List<RentalResponse>> checkOverdueBooks() {
        return BaseResponse.success(managementService.checkOverdueBooks());
    }

    @PostMapping("/send-all-overdue-books-notices")
    public BaseResponse<Void> sendAllOverdueBooksNotices() {
        managementService.sendAllOverdueBooksNotices();
        return BaseResponse.success();
    }

    @PostMapping("/send-overdue-notices/{rentalId}")
    public BaseResponse<Void> sendOverdueNotices(@PathVariable("rentalId") Long rentalId) {
        managementService.sendOverdueNotices(rentalId);
        return BaseResponse.success();
    }

    @PutMapping("/update-book-status/{rentalId}/{rentalStatus}")
    public BaseResponse<Void> updateBookStatus(@PathVariable("rentalId") Long rentalId, @PathVariable("rentalStatus") RentalStatus rentalStatus) {
        managementService.updateBookStatus(rentalId, rentalStatus);
        return BaseResponse.success();
    }

    @PutMapping("/log-return-event/{rentalId}/{returnDate}")
    public BaseResponse<Void> logReturnEvent(@PathVariable("rentalId") Long rentalId, @PathVariable("returnDate") LocalDate returnDate) {
        managementService.logReturnEvent(rentalId, returnDate);
        return BaseResponse.success();
    }

}
