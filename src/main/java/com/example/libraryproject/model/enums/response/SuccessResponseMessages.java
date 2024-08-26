package com.example.libraryproject.model.enums.response;

import com.example.libraryproject.model.dto.response.base.ResponseMessages;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@FieldDefaults(makeFinal = true ,level = AccessLevel.PRIVATE)
public enum SuccessResponseMessages implements ResponseMessages {
    SUCCESS("Success", "Successfully", HttpStatus.OK),
    CREATED("created","Successfully created",HttpStatus.CREATED);


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
