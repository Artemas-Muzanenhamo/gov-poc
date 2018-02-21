package com.gov.zw.service;

public class InvalidIdentityNameException extends Exception {

    static final long serialVersionUID = 1L;

    public InvalidIdentityNameException(String message) {
        super(message);
    }
}
