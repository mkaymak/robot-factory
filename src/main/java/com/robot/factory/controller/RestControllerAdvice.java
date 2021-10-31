package com.robot.factory.controller;

import com.robot.factory.exceptions.ComponentOutOfStockException;
import com.robot.factory.exceptions.ComponentTypesIncompatibilityException;
import com.robot.factory.exceptions.InvalidComponentException;
import com.robot.factory.exceptions.NumberOfComponentsIsNotSufficientException;
import com.robot.factory.model.RobotComponentType;
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
    private final String INCOMPATIBILITY_EXCEPTION_MESSAGE = "Requested order components types are incompatible: ";
    private final String OUT_OF_STOCK_EXCEPTION_MESSAGE = "There is not enough stock for requested order components: ";
    private final String INVALID_COMPONENT_EXCEPTION_MESSAGE = "One of the requested component is not valid: ";
    private final String NUMBER_OF_REQUIRED_COMPONENT_MESSAGE = String.valueOf(RobotComponentType.values().length);
    private final String INSUFFICIENT_NUMBER_OF_COMPONENTS_EXCEPTION_MESSAGE =
            "Requested order components number is not sufficient to build a robot, " +
                    "Expected: " + NUMBER_OF_REQUIRED_COMPONENT_MESSAGE + "Found: ";
    private String GLOBAL_EXCEPTION_MESSAGE = "An exception occurred: ";

    BiFunction<String, WebRequest, ErrorResponse> createErrorResponse = (exception, request) ->
                                new ErrorResponse(new Date(), exception, request.getDescription(false));

    @ExceptionHandler(ComponentTypesIncompatibilityException.class)
    public ResponseEntity componentTypesIncompatibilityException(ComponentTypesIncompatibilityException ex, WebRequest request) {
        logger.error(INCOMPATIBILITY_EXCEPTION_MESSAGE, ex);
        ErrorResponse errorResponse = createErrorResponse.apply(INCOMPATIBILITY_EXCEPTION_MESSAGE, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberOfComponentsIsNotSufficientException.class)
    public ResponseEntity numberOfComponentsIsNotSufficientException(
            NumberOfComponentsIsNotSufficientException ex, WebRequest request) {
        logger.error(INSUFFICIENT_NUMBER_OF_COMPONENTS_EXCEPTION_MESSAGE, ex);
        ErrorResponse errorResponse = createErrorResponse.apply(
                INSUFFICIENT_NUMBER_OF_COMPONENTS_EXCEPTION_MESSAGE + ex.getMessage(), request);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ComponentOutOfStockException.class)
    public ResponseEntity componentOutOfStockException(ComponentOutOfStockException ex, WebRequest request) {
        logger.error(OUT_OF_STOCK_EXCEPTION_MESSAGE, ex);
        ErrorResponse errorResponse = createErrorResponse.apply(
                OUT_OF_STOCK_EXCEPTION_MESSAGE + ex.getMessage(), request);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidComponentException.class)
    public ResponseEntity invalidComponentException(InvalidComponentException ex, WebRequest request) {
        logger.error(INVALID_COMPONENT_EXCEPTION_MESSAGE, ex);
        ErrorResponse errorResponse = createErrorResponse.apply(
                INVALID_COMPONENT_EXCEPTION_MESSAGE + ex.getMessage(), request);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity globalExceptionHandler(Exception ex, WebRequest request) {
        logger.error(GLOBAL_EXCEPTION_MESSAGE, ex);
        ErrorResponse errorResponse = createErrorResponse.apply(GLOBAL_EXCEPTION_MESSAGE, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
