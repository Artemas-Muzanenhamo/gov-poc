package com.gov.zw.controller;

import com.gov.zw.client.exception.IdentityReferenceJsonNotValidException;
import com.gov.zw.exception.InvalidIdentityException;
import com.gov.zw.exception.InvalidLicenseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class LicenseControllerExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(value = BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<InvalidIdentityException> handleInvalidIdentityException(InvalidIdentityException exception) {
        return ResponseEntity.status(BAD_REQUEST).body(exception);
    }

    @ExceptionHandler
    @ResponseStatus(value = BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<InvalidLicenseException> handleInvalidLicenseException(InvalidLicenseException exception) {
        return ResponseEntity.status(BAD_REQUEST).body(exception);
    }

    @ExceptionHandler
    @ResponseStatus(value = BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<IdentityReferenceJsonNotValidException> handleInvalidIdentityReferenceException(IdentityReferenceJsonNotValidException exception) {
        return ResponseEntity.status(BAD_REQUEST).body(exception);
    }
}
