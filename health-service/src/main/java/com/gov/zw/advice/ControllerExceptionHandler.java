package com.gov.zw.advice;

import com.gov.zw.exceptions.InvalidPatientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(InvalidPatientException.class)
    public ResponseEntity<ApiValidationError> invalidSctCaseIdException(InvalidPatientException e) {
        LOGGER.error(e.getMessage(), e);
        return new ResponseEntity<>(new ApiValidationError(e.getMessage()), BAD_REQUEST);
    }
}
