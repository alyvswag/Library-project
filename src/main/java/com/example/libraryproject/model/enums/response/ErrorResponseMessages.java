package com.example.libraryproject.model.enums.response;

import com.example.libraryproject.model.dto.response.base.ResponseMessages;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@FieldDefaults(makeFinal = true ,level = AccessLevel.PRIVATE)
public enum ErrorResponseMessages implements ResponseMessages {
    UNEXPECTED("unexpected", "Unexpected error.", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND("not_found_%s", "The requested %s model with %s was not found.", HttpStatus.NOT_FOUND),
    EMAIL_ALREADY_REGISTERED("email_already_registered", "Email already registered", HttpStatus.CONFLICT),
    INVALID_EMAIL_FORMAT("invalid_email_format", "Email format is invalid", HttpStatus.BAD_REQUEST),
    NULL_NOT_ALLOWED("null_not_allowed_%s", "The column %s does not allow null values.", HttpStatus.BAD_REQUEST);
    String key;
    String message;
    HttpStatus httpStatus;


    @Override
    public String key() {
        return key;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }
}
