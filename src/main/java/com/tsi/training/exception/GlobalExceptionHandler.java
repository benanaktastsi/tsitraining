package com.tsi.training.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.logging.Level;
import java.util.logging.Logger;



@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value
            = NoPartExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse
    handleException(NoPartExistsException ex)
    {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.SEVERE, "An exception was thrown (no such part exists)", ex);
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}