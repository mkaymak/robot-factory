package com.robot.factory.controller;

import com.robot.factory.exceptions.ComponentTypesIncompatibilityException;
import com.robot.factory.exceptions.NumberOfComponentsIsNotSufficientException;
import com.robot.factory.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Date;
import java.util.function.BiFunction;

@ControllerAdvice
@EnableWebMvc
public class RestControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(RestControllerAdvice.class);
    private final String INCOMPATIBILITY_EXCEPTION_TEXT = "Requested order components types are incompatible: ";
    private final String INSUFFICIENT_NUMBER_OF_COMPONENTS_EXCEPTION_TEXT =
            "Requested order components number is not sufficient to build a robot ";
    private String GLOBAL_EXCEPTION_TEXT = "An exception occurred: ";

    BiFunction<String, WebRequest, ErrorResponse> createErrorResponse = (exception, request) ->
                                new ErrorResponse(new Date(), exception, request.getDescription(false));

    @ExceptionHandler(ComponentTypesIncompatibilityException.class)
    public ResponseEntity componentTypesIncompatibilityException(ComponentTypesIncompatibilityException ex, WebRequest request) {
        logger.error(INCOMPATIBILITY_EXCEPTION_TEXT, ex);
        ErrorResponse errorResponse = createErrorResponse.apply(INCOMPATIBILITY_EXCEPTION_TEXT, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberOfComponentsIsNotSufficientException.class)
    public ResponseEntity numberOfComponentsIsNotSufficientException(
            NumberOfComponentsIsNotSufficientException ex, WebRequest request) {
        logger.error(INSUFFICIENT_NUMBER_OF_COMPONENTS_EXCEPTION_TEXT, ex);
        ErrorResponse errorResponse = createErrorResponse.apply(INSUFFICIENT_NUMBER_OF_COMPONENTS_EXCEPTION_TEXT, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity globalExceptionHandler(Exception ex, WebRequest request) {
        logger.error(GLOBAL_EXCEPTION_TEXT, ex);
        ErrorResponse errorResponse = createErrorResponse.apply(GLOBAL_EXCEPTION_TEXT, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
