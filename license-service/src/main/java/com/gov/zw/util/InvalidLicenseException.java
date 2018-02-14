package com.gov.zw.util;

public class InvalidLicenseException extends Exception {

    static final long serialVersionUID = 1L;

    public InvalidLicenseException(String message){
        super(message);
    }
}
