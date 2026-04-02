package com.ecommerce.entity;

import lombok.Data;

@Data
public class ApiError {

    private int status;
    private String message;
    private long timestamp;
    private String path;

    public ApiError(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
        this.path = path;
    }

    // getters
}