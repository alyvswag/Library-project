package com.example.libraryproject.service.management;

import com.example.libraryproject.exception.BaseException;
import com.example.libraryproject.mapper.notification.NotificationMapper;
import com.example.libraryproject.mapper.rental.RentalMapper;
import com.example.libraryproject.model.dao.Rental;
import com.example.libraryproject.model.dto.request.create.NotificationRequestCreate;
import com.example.libraryproject.model.dto.response.admin.RentalResponseAdmin;
import com.example.libraryproject.model.enums.rental.RentalStatus;
import com.example.libraryproject.repository.notification.NotificationRepository;
import com.example.libraryproject.repository.rental.RentalRepository;
import com.example.libraryproject.service.book.BookManagementService;
import com.example.libraryproject.service.email.EmailProducer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.example.libraryproject.model.enums.notification.DataType.REMINDER;
import static com.example.libraryproject.model.enums.rental.RentalStatus.RETURNED;
import static com.example.libraryproject.model.enums.response.ErrorResponseMessages.RENTAL_NOT_OVERDUE;
import static com.example.libraryproject.utils.CommonUtils.throwIf;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class ManagementServiceImpl implements ManagementService {

    final RentalRepository rentalRepository;
    final NotificationRepository notificationRepository;
    final RentalMapper rentalMapper;
    final NotificationMapper notificationMapper;
    final EmailProducer emailProducer;
    final BookManagementService bookManagementService;

    @Override
    public List<RentalResponseAdmin> checkOverdueBooks() {
        return rentalMapper.toDtoListRentalResponseAdminModel(rentalRepository.findRentalOverdue(LocalDate.now()));
    }

    @Override
    public void sendAllOverdueBooksNotices() {
        List<Rental> rentalOverdueList = rentalRepository.findRentalOverdue(LocalDate.now());
        for (Rental rental : rentalOverdueList) {
            sendOverdueBookNotificationAndSaveNotification(rental);
        }
    }

    @Override
    public void sendOverdueNotices(Long rentalId) {
        Rental rentalEntity = rentalRepository.findRentalById(rentalId).orElseThrow(
                () -> BaseException.notFound(Rental.class.getSimpleName(), "rental", String.valueOf(rentalId))
        );
        throwIf(
                () -> isRentalStillActive(rentalEntity),// bura false geldikde avtomatik exceptionimiz atilacaq
                BaseException.of(RENTAL_NOT_OVERDUE)
        );
        sendOverdueBookNotificationAndSaveNotification(rentalEntity);
    }

    @Override
    public void updateBookStatus(Long rentalId, RentalStatus rentalStatus) {
        Rental rentalEntity = findRentalByIdIsActive(rentalId);
        rentalEntity.setRentalStatus(rentalStatus);
        if(rentalStatus==RETURNED){
            rentalEntity.setReturnDate(LocalDate.now());
            bookManagementService.rentalQuantity(rentalEntity.getBook(),-1);
        }
        rentalRepository.save(rentalEntity);
    }

    @Override
    public void logReturnEvent(Long rentalId, LocalDate returnDate) {//todo : bunu bele basa dusmusem
        Rental rentalEntity = findRentalByIdIsActive(rentalId);
        rentalEntity.setRentalStatus(RETURNED);
        rentalEntity.setReturnDate(returnDate);
        bookManagementService.rentalQuantity(rentalEntity.getBook(),-1);
        rentalRepository.save(rentalEntity);
    }


    //private

    private boolean isRentalStillActive(Rental rental) {
        return rental.getRentalEndDate().isAfter(LocalDate.now());
        // kiraye muddeti bitmeyibse true atacaq
        // bitibse false
    }

    private void sendOverdueBookNotificationAndSaveNotification(Rental rental) {
        Long days = ChronoUnit.DAYS.between(rental.getRentalEndDate(), LocalDate.now());
        emailProducer.sendOverdueReminderNotification(
                rental.getUser().getEmail(), rental.getBook().getBookName(), String.valueOf(days));
        notificationRepository.save(notificationMapper.toEntity(NotificationRequestCreate.builder()
                .userId(rental.getUser().getId())
                .dataType(REMINDER)
                .message("Sizin kirayə götürdüyünüz kitabın (" + rental.getBook().getBookName() + ") qaytarılma müddətindən " + days + "gün keçmişdir. Xahiş edirik, kitabı ən qısa zamanda geri qaytarasınız")
                .build()
        ));
    }
        private Rental findRentalByIdIsActive(Long id){
            return rentalRepository.findRentalByIdIsActive(id).orElseThrow(
                    () -> BaseException.notFound(Rental.class.getSimpleName(), "rental", String.valueOf(id))
            );
        }


}
