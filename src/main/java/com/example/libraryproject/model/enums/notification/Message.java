package com.example.libraryproject.model.enums.notification;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum Message {
    REMINDER_MESSAGE("Sizin kirayə götürdüyünüz kitabın (%s) kirayə müddətinin bitməsinə %s gün qalıb."),
    NEW_BOOK_MESSAGE("Kitabxanamızda yeni %s kitabı var."),
    EVENT_MESSAGE("Kitabxanamızda %s tedbiri olacaq. Dəvətlisiniz."),
    OVERDUE_BOOK_MESSAGE("Sizin kirayə götürdüyünüz kitabın (%s)  qaytarılma müddətindən %s gün keçmişdir. Xahiş edirik, kitabı ən qısa zamanda geri qaytarasınız.")
    ;

    String message;

    public String message() {
        return message;
    }
}
