package com.app.exception;

import com.app.model.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(value = {EmployeeNotFoundException.class})
    public ResponseEntity<ApiException> handleEmployeeNotFoundException(EmployeeNotFoundException ex, WebRequest request) {
        LOGGER.warn("EmployeeNotFoundException Occurred :: {}", ex.getLocalizedMessage());
        ApiException apiException = new ApiException(ex.getMessage(), request.getContextPath(), ex.getCause(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {HttpClientErrorException.Unauthorized.class})
    public ResponseEntity<ApiException> handleUnAuthorizedException(HttpClientErrorException.Unauthorized ex, WebRequest request) {
        LOGGER.warn("UnAuthorized Exception Occurred :: {}", ex.getLocalizedMessage());
        ApiException apiException = new ApiException(ex.getMessage(), request.getContextPath(), ex.getCause(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiException> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        LOGGER.warn("UnAuthorized Exception Occurred :: {}", ex.getLocalizedMessage());
        ApiException apiException = new ApiException(ex.getMessage(), request.getContextPath(), ex.getCause(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

}