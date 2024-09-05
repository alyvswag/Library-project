package com.example.libraryproject.mapper.rental;

import com.example.libraryproject.model.dao.Rental;
import com.example.libraryproject.model.dao.Reservation;
import com.example.libraryproject.model.dto.request.create.RentalRequestCreate;
import com.example.libraryproject.model.dto.request.update.RentalRequestUpdate;
import com.example.libraryproject.model.dto.response.admin.RentalResponseAdmin;
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
    RentalResponseAdmin toDtoUserActivity (Rental rental);

    @IterableMapping(qualifiedByName = "toDtoUserActivity")
    List<RentalResponseAdmin> toDtoUserActivity(List<Rental> rentals);

    @Mapping(target = "book", ignore = true)
    @Named(value = "toDtoBookRentalHistory")
    RentalResponseAdmin toDtoBookRentalHistory (Rental rental);

    @IterableMapping(qualifiedByName = "toDtoBookRentalHistory")
    List<RentalResponseAdmin> toDtoBookRentalHistory(List<Rental> rentals);

    @Named(value = "toDtoListRentalResponseAdminModel")
    RentalResponseAdmin toDtoListRentalResponseAdminModel (Rental rental);

    @IterableMapping(qualifiedByName = "toDtoListRentalResponseAdminModel")
    List<RentalResponseAdmin> toDtoListRentalResponseAdminModel(List<Rental> rentals);
}
