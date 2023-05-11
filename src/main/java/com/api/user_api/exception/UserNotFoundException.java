package com.api.user_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class handles the user not exceptions thrown by the API
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception {

    private static final long serialVersionUID = 1;

    public UserNotFoundException(String message){
        super(message);
    }
}
