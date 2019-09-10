package com.gov.zw.controller;

import com.gov.zw.util.InvalidIdentityException;
import com.gov.zw.util.InvalidLicenseException;
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
    public InvalidIdentityException handleInvalidIdentityException(InvalidIdentityException exception) {
        return new InvalidIdentityException(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(value = BAD_REQUEST)
    @ResponseBody
    public InvalidLicenseException handleInvalidLicenseException(InvalidLicenseException exception) {
        return new InvalidLicenseException(exception.getMessage());
    }
}
