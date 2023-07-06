package com.book.booksproject.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {

   @ExceptionHandler(value = RecordNotFoundEX.class)
    public ResponseEntity<?> recordNotFound(Exception ex){
    ErrorResponse response= ErrorResponse.builder()
            .success(false)
            .dateTime(LocalDateTime.now())
            .details(Arrays.asList(ex.getMessage()))
            .errorMessage(ex.getLocalizedMessage())
            .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

   }
    @ExceptionHandler(value = DuplicatedRecordEX.class)
    public ResponseEntity<?> duplicatedRecord(Exception ex){
        ErrorResponse response= ErrorResponse.builder()
                .success(false)
                .dateTime(LocalDateTime.now())
                .details(Arrays.asList(ex.getMessage()))
                .errorMessage(ex.getLocalizedMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

    }
    @ExceptionHandler(value = FileStorageEX.class)
    public ResponseEntity<?> fileStorage(Exception ex){
        ErrorResponse response= ErrorResponse.builder()
                .success(false)
                .dateTime(LocalDateTime.now())
                .details(Arrays.asList(ex.getMessage()))
                .errorMessage(ex.getLocalizedMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getDefaultMessage());
        }

        ErrorResponse error = ErrorResponse.builder()
                .success(false)
                .dateTime(LocalDateTime.now())
                .details(errors)
                .errorMessage(ex.getLocalizedMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }
}
