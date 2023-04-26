package com.example.bankservice.exception;

import lombok.Data;

@Data
public class BankAccountNotFoundException extends RuntimeException {
    public String id = "notFound";

    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
