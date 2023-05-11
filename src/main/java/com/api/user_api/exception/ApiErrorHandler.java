package com.api.user_api.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class handles the exceptions thrown by the API
 */
@RestControllerAdvice
@Slf4j
public class ApiErrorHandler {

    // Handle the exceptions
    // Handle the exception thrown when a bad request is made
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ApiError> badRequestException(BadRequestException e, HttpServletRequest request){
        log.error("Bad request exception: " + e.getLocalizedMessage() + " for " + request.getRequestURI());
        return new ResponseEntity<>(
                ApiError.builder().errorMessage(e.getLocalizedMessage()).errorCode(HttpStatus.BAD_REQUEST.toString()).request(request.getRequestURI()).requestType(request.getMethod()).customMessage("Request is not valid").build(), HttpStatus.BAD_REQUEST);
    }

    // Handle the exception thrown when a user already exists
    @ExceptionHandler({UserExistsException.class})
    public ResponseEntity<ApiError> userExistsException(UserExistsException e, HttpServletRequest request){
        log.error("User already exists exception: " + e.getLocalizedMessage() + " for " + request.getRequestURI());
        return new ResponseEntity<>(
                ApiError.builder().errorMessage(e.getLocalizedMessage()).errorCode(HttpStatus.CONFLICT.toString()).request(request.getRequestURI()).requestType(request.getMethod()).customMessage("This user already exists").build(), HttpStatus.CONFLICT);
    }

    // Handle the exception thrown when a user is not found
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ApiError> userNotFoundException(UserNotFoundException e, HttpServletRequest request){
        log.error("User not found exception: " + e.getLocalizedMessage() + " for " + request.getRequestURI());
        return new ResponseEntity<>(
                ApiError.builder().errorMessage(e.getLocalizedMessage()).errorCode(HttpStatus.NOT_FOUND.toString()).request(request.getRequestURI()).requestType(request.getMethod()).customMessage("The user is not found").build(), HttpStatus.NOT_FOUND);
    }

    // Handle the exception thrown when the credentials are invalid
    @ExceptionHandler({InvalidCredentialsException.class})
    public ResponseEntity<ApiError> invalidCredentialsException(InvalidCredentialsException e, HttpServletRequest request){
        log.error("Invalid credentials exception: " + e.getLocalizedMessage() + " for " + request.getRequestURI());
        return new ResponseEntity<>(
                ApiError.builder().errorMessage(e.getLocalizedMessage()).errorCode(HttpStatus.UNAUTHORIZED.toString()).request(request.getRequestURI()).requestType(request.getMethod()).customMessage("User credentials are invalid").build(), HttpStatus.UNAUTHORIZED);
    }
}
