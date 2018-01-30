package com.gov.zw.service;

import com.gov.zw.domain.License;
import com.gov.zw.util.IdentityInvalidException;

public interface LicenseService {
    void addLicense(License license) throws IdentityInvalidException;
}
