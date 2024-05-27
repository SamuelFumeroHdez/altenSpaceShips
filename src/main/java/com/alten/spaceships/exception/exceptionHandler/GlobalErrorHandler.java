package com.alten.spaceships.exception.exceptionHandler;

import com.alten.spaceships.entity.error.Error;
import com.alten.spaceships.entity.error.ErrorResponse;
import com.alten.spaceships.exception.ExistingSpaceShipException;
import com.alten.spaceships.exception.SpaceShipNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(SpaceShipNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerSpaceShipNotFoundException(SpaceShipNotFoundException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errors(Arrays.asList(Error.builder()
                        .code("SPACESHIP_NOT_FOUND")
                        .message(ex.getMessage())
                        .build()))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ExistingSpaceShipException.class)
    public ResponseEntity<ErrorResponse> handlerExistingSpaceShipException(ExistingSpaceShipException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errors(Arrays.asList(Error.builder()
                        .code("EXISTING_SPACESHIP")
                        .message(ex.getMessage())
                        .build()))
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
