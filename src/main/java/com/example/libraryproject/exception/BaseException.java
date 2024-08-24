package com.example.libraryproject.exception;

import com.example.libraryproject.model.dto.response.base.ResponseMessages;

import static com.example.libraryproject.enums.response.ErrorResponseMessages.*;

import com.example.libraryproject.exception.types.NotFoundExceptionType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class BaseException extends RuntimeException {
    ResponseMessages responseMessages;
    NotFoundExceptionType notFoundData;

    @Override
    public String getMessage() {
        return responseMessages.message();
    }

    public static BaseException of(ResponseMessages responseMessage) {
        return BaseException.builder()
                .responseMessages(responseMessage)
                .build();
    }

    public static BaseException unexpected() {
        return of(UNEXPECTED);
    }

    public static BaseException notFound(String target, String field, Object value) {
        return BaseException.builder()
                .responseMessages(NOT_FOUND)
                .notFoundData(
                        NotFoundExceptionType.of(target, Map.of(field, value))
                )
                .build();
    }


}
