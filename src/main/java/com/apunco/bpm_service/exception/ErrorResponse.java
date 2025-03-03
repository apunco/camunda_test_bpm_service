package com.apunco.bpm_service.exception;

import lombok.Getter;

@Getter
public class ErrorResponse extends RuntimeException {
    private final String code;

    public ErrorResponse(String code, String message) {
        super(message);
        this.code = code;
    }
}
