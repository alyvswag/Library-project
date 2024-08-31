package com.example.libraryproject.exception;

import com.example.libraryproject.model.dto.response.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<BaseResponse<?>> handleBaseException( BaseException  ex) {
        return  ResponseEntity.status(ex.getResponseMessages().httpStatus()).body(BaseResponse.error(ex));
    }
    @ExceptionHandler
    public ResponseEntity<BaseResponse<?>> handleBaseException( SQLException  ex) {
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseResponse.error(ex));
    }

    // qeyd: ResponseEntity, HTTP cavablarını (HTTP Response) bərpa etmək üçün istifadə olunan bir sinifdir.
    // Bu siniflə HTTP status kodunu (status), başlıqları (headers), və bədənini (body) idarə etmək mümkündür.
    // Yəni, hansı cavabı verəcəyini, hansı status kodu ilə verəcəyini və
    // hansı məlumatı göndərəcəyini buradan müəyyən edirsən.

//    @ExceptionHandler(SQLException.class)
//    public ResponseEntity<Map<String, String>> handleSqlException(SQLException ex) {
//        Map<String, String> response = new HashMap<>();
//        response.put("error", ex.getMessage().split("Ayrıntı:")[0]);
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    // todo : ya global yada sexsi exception class ataciq heleki global ataciq

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
//        Map<String, String> response = new HashMap<>();
//        response.put("error", "Something went wrong. Please try again later.");
//        response.put("details", ex.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//    }
}
