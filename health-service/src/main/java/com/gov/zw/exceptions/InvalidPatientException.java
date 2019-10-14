package com.gov.zw.exceptions;

public class InvalidPatientException extends RuntimeException {

    public InvalidPatientException(String message) {
        super(message);
    }
}
