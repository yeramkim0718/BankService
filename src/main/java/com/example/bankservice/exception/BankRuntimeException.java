package com.example.bankservice.exception;

import lombok.Data;

@Data
public class BankRuntimeException extends RuntimeException {

    private String id;

    public BankRuntimeException(String id, String message) {
        super(message);
        this.id = id;
    }
}
