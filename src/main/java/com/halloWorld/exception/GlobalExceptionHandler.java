package com.halloWorld.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

  // handle specific exception
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
      ResourceNotFoundException exception, WebRequest webRequest) {

    ErrorDetails errorDetails =
        new ErrorDetails(
            new Date(),
            exception.getMessage(),
            webRequest.getDescription(false),
            HttpStatus.NOT_FOUND);

    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }
}
