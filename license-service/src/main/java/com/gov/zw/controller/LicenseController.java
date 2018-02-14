package com.gov.zw.controller;

import com.gov.zw.domain.License;
import com.gov.zw.repository.LicenseRepository;
import com.gov.zw.service.LicenseService;
import com.gov.zw.util.IdentityInvalidException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/licenses")
public class LicenseController {

    private final LicenseRepository licenseRepository;
    private final LicenseService licenseServiceImpl;

    public LicenseController(LicenseRepository licenseRepository, LicenseService licenseServiceImpl){
        this.licenseRepository = licenseRepository;
        this.licenseServiceImpl = licenseServiceImpl;
    }

    // Create
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void addLicense(@RequestBody License license) throws IdentityInvalidException {
        this.licenseServiceImpl.addLicense(license);
    }

    // Read
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<License> getAllLicenses() {
        return this.licenseServiceImpl.getAllLicenses();
    }

    // Update
    @PutMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void updateLicense(@RequestBody License license) throws IdentityInvalidException {
        this.licenseServiceImpl.updateLicense(license);
    }

    // Delete
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteLicense(@RequestBody License license) {
        this.licenseRepository.delete(license);
    }

}
