package com.robot.factory.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ComponentTypesIncompatibilityException extends RuntimeException {

    public ComponentTypesIncompatibilityException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComponentTypesIncompatibilityException(String message) {
        super(message);
    }

    public ComponentTypesIncompatibilityException(Throwable cause) {
        super(cause);
    }
}