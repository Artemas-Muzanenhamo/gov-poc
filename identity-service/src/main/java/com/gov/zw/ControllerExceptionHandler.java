package com.gov.zw;

import com.gov.zw.exception.InvalidIdentityException;
import com.gov.zw.service.ApiValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(InvalidIdentityException.class)
    public ResponseEntity<ApiValidationError> invalidIdentityExceptionResponseEntity(InvalidIdentityException e) {
        LOGGER.error(e.getMessage(), e);
        return new ResponseEntity<>(new ApiValidationError(e.getMessage()), BAD_REQUEST);
    }
}