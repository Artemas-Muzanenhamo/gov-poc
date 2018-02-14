package com.gov.zw.service;

import com.gov.zw.domain.License;
import com.gov.zw.util.IdentityInvalidException;
import com.gov.zw.util.InvalidLicenseException;

import java.util.List;

public interface LicenseService {
    void addLicense(License license) throws IdentityInvalidException;

    List<License> getAllLicenses();

    void updateLicense(License license) throws InvalidLicenseException;

    void removeLicense(License license) throws InvalidLicenseException;
}
