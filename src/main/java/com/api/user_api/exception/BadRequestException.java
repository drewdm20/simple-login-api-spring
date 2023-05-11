package com.api.user_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class handles the bad request exceptions thrown by the API
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception{

    private static final long serialVersionUID = 1;

    public BadRequestException(String message){
        super(message);
    }
}
