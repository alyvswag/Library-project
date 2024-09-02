package com.example.libraryproject.controller.v1.rental;

import com.example.libraryproject.model.dao.Rental;
import com.example.libraryproject.model.dto.request.create.RentalRequestCreate;
import com.example.libraryproject.model.dto.response.base.BaseResponse;
import com.example.libraryproject.service.rental.RentalService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rentals")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RentalController {
    final RentalService rentalService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add-rental/{reservationId}")
    public BaseResponse<Void> addRental(@PathVariable Long reservationId, @RequestBody RentalRequestCreate request) {
        rentalService.addRental(reservationId, request);
        return BaseResponse.success();
    }

}
