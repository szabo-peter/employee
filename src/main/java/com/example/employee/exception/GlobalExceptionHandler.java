package com.example.employee.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CsokiAndrasException.class)
    public List<String> handleValidationException(
            CsokiAndrasException validationException
    ) {
        log.debug("Validation exception happen: ", validationException);
        return List.of(validationException.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public List<String> handleEverything(
            Throwable throwable) {
        log.error("Something went wrong: ", throwable);
        return List.of(throwable.getMessage());
    }

}
