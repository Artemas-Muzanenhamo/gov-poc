package com.gov.zw.service;

import com.gov.zw.client.dto.IdentityReference;
import com.gov.zw.domain.LicenseJson;
import com.gov.zw.dto.License;
import com.gov.zw.exception.InvalidIdentityException;
import com.gov.zw.exception.InvalidLicenseException;

import java.util.List;

public interface LicenseService {
    void addLicense(License license) throws InvalidIdentityException, InvalidLicenseException;

    List<License> getAllLicenses();

    void updateLicense(LicenseJson licenseJson) throws InvalidLicenseException;

    void removeLicense(LicenseJson licenseJson) throws InvalidLicenseException;

    License getLicenseByIdentityRef(IdentityReference identityReference) throws InvalidLicenseException;
}
