package com.bt.assessment.admin.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class UserNotFoundExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(UserNotFoundExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public String handleResourceNotFoundException(final UserNotFoundException e) {
        logger.error(e.getMessage());
        return e.getMessage();
    }
}
