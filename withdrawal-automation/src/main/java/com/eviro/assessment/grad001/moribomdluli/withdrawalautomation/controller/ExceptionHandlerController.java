package com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.controller;

import com.eviro.assessment.grad001.moribomdluli.withdrawalautomation.exception.ProductException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductException.class)
    private String handleUserExistsException(Exception exc){
        return exc.getMessage();
    }
}
