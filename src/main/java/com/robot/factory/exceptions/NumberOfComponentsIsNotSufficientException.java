package com.robot.factory.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NumberOfComponentsIsNotSufficientException extends RuntimeException {

    public NumberOfComponentsIsNotSufficientException(String message, Throwable cause) {
        super(message, cause);
    }

    public NumberOfComponentsIsNotSufficientException(String message) {
        super(message);
    }

    public NumberOfComponentsIsNotSufficientException(Throwable cause) {
        super(cause);
    }
}
