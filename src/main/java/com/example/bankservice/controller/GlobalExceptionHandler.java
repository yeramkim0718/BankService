package com.example.bankservice.controller;

import com.example.bankservice.exception.BankAccountNotFoundException;
import com.example.bankservice.exception.BankRuntimeException;
import com.example.bankservice.model.restresult.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.LinkedHashMap;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BankAccountNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResult bankAccountNotFoundException(BankAccountNotFoundException e) {
        
        RestResult restResult = new RestResult();
        restResult.setId(e.getId());
        restResult.setMessage(e.getMessage());

        return restResult;
    }

    @ExceptionHandler(BankRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResult bankAccountNotFoundException(BankRuntimeException e) {

        RestResult restResult = new RestResult();
        restResult.setId(e.getId());
        restResult.setMessage(e.getMessage());

        return restResult;
    }

    // else
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResult bankAccountNotFoundException(Exception e) {

        RestResult restResult = new RestResult();
        restResult.setId("serverError");
        restResult.setMessage(e.getMessage());

        return restResult;
    }
}
