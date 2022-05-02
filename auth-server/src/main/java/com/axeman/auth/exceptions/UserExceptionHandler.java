package com.axeman.auth.exceptions;

import com.axeman.auth.models.http.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Date;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<ErrorResponse> userCreationExceptionHandler(Exception exception) {
        ErrorResponse errorResponse = getErrorResponse(exception, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> genericExceptionHandler(Exception exception) {
        ErrorResponse errorResponse = getErrorResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    private ErrorResponse getErrorResponse(Exception exception, HttpStatus httpStatus) {
        return ErrorResponse.builder()
                .errorMessage(exception.getMessage())
                .errorCode(exception.getMessage())
                .status(httpStatus.name())
                .statusCode(httpStatus.value())
                .exceptionDate(Date.from(Instant.now()))
                .build();
    }
}
