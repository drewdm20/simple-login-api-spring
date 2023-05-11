package com.api.user_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class handles the user exists exceptions thrown by the API
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserExistsException extends Exception{

    private static long serialVersionUID = 1;

    public UserExistsException(String message){
        super(message);
    }
}
