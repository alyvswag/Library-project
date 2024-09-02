package com.example.libraryproject.mapper.rental;

import com.example.libraryproject.model.dao.Book;
import com.example.libraryproject.model.dao.Rental;
import com.example.libraryproject.model.dao.Reservation;
import com.example.libraryproject.model.dto.request.create.RentalRequestCreate;
import com.example.libraryproject.model.dto.request.update.BookRequestUpdate;
import com.example.libraryproject.service.book.BookService;
import com.example.libraryproject.service.user.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",uses = {UserService.class, BookService.class},nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RentalMapper {

    @Mapping(source = "book", target = "book")
    @Mapping(source = "user", target = "user")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rentalStatus", constant = "ACTIVE")
    Rental toEntityRental(Reservation reservation);

    void dtoModelMappingDao(RentalRequestCreate dto, @MappingTarget Rental rental);

}
