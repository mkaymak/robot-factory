package com.robot.factory.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ComponentOutOfStockException extends RuntimeException {

    public ComponentOutOfStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComponentOutOfStockException(String message) {
        super(message);
    }

    public ComponentOutOfStockException(Throwable cause) {
        super(cause);
    }
}