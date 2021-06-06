package com.example.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CsokiAndrasException extends RuntimeException {

    public CsokiAndrasException (String message){
        super(message);
    }
}
