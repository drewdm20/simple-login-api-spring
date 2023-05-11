package com.api.user_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class handles the invalid credentials exceptions thrown by the API
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class InvalidCredentialsException extends Exception{

    private static final long serialVersionUID = 1;

    public InvalidCredentialsException(String message){
        super(message);
    }
}
