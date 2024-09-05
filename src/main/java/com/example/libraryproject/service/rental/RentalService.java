package com.example.libraryproject.service.rental;

import com.example.libraryproject.model.dto.request.create.RentalRequestCreate;
import com.example.libraryproject.model.dto.request.update.RentalRequestUpdate;

public interface RentalService {
    void addRental(Long reservationId, RentalRequestCreate rentalRequestCreate);
    void updateRental(Long rentalId, RentalRequestUpdate rentalRequestUpdate);
    void confirmedRental(Long rentalId);
}
