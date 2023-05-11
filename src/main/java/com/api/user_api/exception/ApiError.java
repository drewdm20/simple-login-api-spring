package com.api.user_api.exception;

import lombok.Builder;
import lombok.Data;

/**
 * This class is used to handle the error response
 */
@Builder
@Data
public class ApiError {

    private String errorMessage;

    private String errorCode;

    private String request;

    private String requestType;

    private String customMessage;
}
