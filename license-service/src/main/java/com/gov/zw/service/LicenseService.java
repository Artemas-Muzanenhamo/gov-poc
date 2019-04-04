package com.gov.zw.service;

import com.gov.zw.domain.License;
import com.gov.zw.domain.LicenseJson;
import com.gov.zw.util.InvalidIdentityException;
import com.gov.zw.util.InvalidLicenseException;

import java.util.List;

public interface LicenseService {
    void addLicense(LicenseJson licenseJson) throws InvalidIdentityException, InvalidLicenseException;

    List<License> getAllLicenses();

    void updateLicense(LicenseJson licenseJson) throws InvalidLicenseException;

    void removeLicense(LicenseJson licenseJson) throws InvalidLicenseException;

    License getLicenseByIdentityRef(String identityRef) throws InvalidLicenseException;

}
