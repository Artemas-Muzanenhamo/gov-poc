package com.gov.zw.service;

import com.gov.zw.domain.License;
import com.gov.zw.util.IdentityInvalidException;

import java.util.List;

public interface LicenseService {
    void addLicense(License license) throws IdentityInvalidException;

    List<License> getAllLicenses();

    void updateLicense(License license) throws IdentityInvalidException;
}
