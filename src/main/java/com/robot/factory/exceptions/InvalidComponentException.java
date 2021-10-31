package com.robot.factory.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidComponentException extends RuntimeException {

    public InvalidComponentException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidComponentException(String message) {
        super(message);
    }

    public InvalidComponentException(Throwable cause) {
        super(cause);
    }
}