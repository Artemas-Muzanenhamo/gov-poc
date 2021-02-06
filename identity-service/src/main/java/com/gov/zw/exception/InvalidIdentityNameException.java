package com.gov.zw.exception;

public class InvalidIdentityNameException extends Exception {

    static final long serialVersionUID = 1L;

    public InvalidIdentityNameException(String message) {
        super(message);
    }
}
