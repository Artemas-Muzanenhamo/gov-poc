package com.gov.zw.exception;

public class InvalidIdentityReferenceException extends Exception {

    static final long serialVersionUID = 1L;

    public InvalidIdentityReferenceException(String message) {
        super(message);
    }
}
