package com.gov.zw.controller;

import com.gov.zw.domain.License;
import com.gov.zw.repository.LicenseRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/license")
public class LicenseController {

    private final LicenseRepository licenseRepository;

    public LicenseController(LicenseRepository licenseRepository){
        this.licenseRepository = licenseRepository;
    }

    // Create

    // Read
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<License> getAllLicenses() {
        return this.licenseRepository.findAll();
    }

    // Update

    // Delete
    @DeleteMapping(value = "/remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteLicense(@RequestBody License license) {
        this.licenseRepository.delete(license);
    }

}
