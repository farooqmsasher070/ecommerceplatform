package com.ecommerce.exception;

import lombok.Data;

public class InvalidRequestException extends RuntimeException {

    private String errorCode;

    public InvalidRequestException(String message) {
        super(message);
    }

    public InvalidRequestException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
