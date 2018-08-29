package com.gov.zw.controller;

import com.gov.zw.domain.License;
import com.gov.zw.service.LicenseService;
import com.gov.zw.util.InvalidIdentityException;
import com.gov.zw.util.InvalidLicenseException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/licenses")
public class LicenseController {
    private final LicenseService licenseServiceImpl;

    public LicenseController(LicenseService licenseServiceImpl) {
        this.licenseServiceImpl = licenseServiceImpl;
    }

    // Create
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void addLicense(@RequestBody License license) throws InvalidLicenseException, InvalidIdentityException {
        this.licenseServiceImpl.addLicense(license);
    }

    // Read
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<License> getAllLicenses() {
        return this.licenseServiceImpl.getAllLicenses();
    }

    // Update
    @PutMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void updateLicense(@RequestBody License license) throws InvalidLicenseException {
        this.licenseServiceImpl.updateLicense(license);
    }

    // Delete
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteLicense(@RequestBody License license) throws InvalidLicenseException {
        this.licenseServiceImpl.removeLicense(license);
    }

}
