package com.gov.zw.service;

import com.gov.zw.client.dto.IdentityReference;
import com.gov.zw.dto.License;
import com.gov.zw.exception.InvalidIdentityException;
import com.gov.zw.exception.InvalidLicenseException;

import java.util.List;

public interface LicenseService {
    void addLicense(License license) throws InvalidIdentityException, InvalidLicenseException;

    List<License> getAllLicenses();

    void updateLicense(License license) throws InvalidLicenseException;

    void removeLicense(License license) throws InvalidLicenseException;

    License getLicenseByIdentityRef(IdentityReference identityReference) throws InvalidLicenseException;
}
