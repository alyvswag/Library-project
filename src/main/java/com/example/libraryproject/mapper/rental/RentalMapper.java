package com.example.libraryproject.mapper.rental;

import com.example.libraryproject.model.dao.Rental;
import com.example.libraryproject.model.dao.Reservation;
import com.example.libraryproject.model.dto.request.create.RentalRequestCreate;
import com.example.libraryproject.model.dto.request.update.RentalRequestUpdate;
import com.example.libraryproject.model.dto.response.payload.RentalResponse;
import com.example.libraryproject.service.book.BookService;
import com.example.libraryproject.service.user.UserService;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserService.class, BookService.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RentalMapper {

    @Mapping(source = "book", target = "book")
    @Mapping(source = "user", target = "user")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rentalStatus", constant = "ACTIVE")
    Rental toEntityRental(Reservation reservation);

    void dtoModelMappingDao(RentalRequestCreate dto, @MappingTarget Rental rental);

    void updateRentalFromDto(RentalRequestUpdate dto, @MappingTarget Rental rental);

    @Named(value = "toDtoUserActivity")
    @Mapping(target = "user", ignore = true)
    RentalResponse toDtoUserActivity (Rental rental);

    @IterableMapping(qualifiedByName = "toDtoUserActivity")
    List<RentalResponse> toDtoUserActivity(List<Rental> rentals);

    @Mapping(target = "book", ignore = true)
    @Named(value = "toDtoBookRentalHistory")
    RentalResponse toDtoBookRentalHistory (Rental rental);

    @IterableMapping(qualifiedByName = "toDtoBookRentalHistory")
    List<RentalResponse> toDtoBookRentalHistory(List<Rental> rentals);

    @Named(value = "toDtoListRentalResponseModel")
    RentalResponse toDtoListRentalResponseModel (Rental rental);

    @IterableMapping(qualifiedByName = "toDtoListRentalResponseModel")
    List<RentalResponse> toDtoListRentalResponseModel(List<Rental> rentals);
}
