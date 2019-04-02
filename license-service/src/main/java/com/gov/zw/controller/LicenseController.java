package com.gov.zw.controller;

import com.gov.zw.domain.License;
import com.gov.zw.domain.LicenseJson;
import com.gov.zw.service.LicenseService;
import com.gov.zw.util.InvalidIdentityException;
import com.gov.zw.util.InvalidLicenseException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/licenses")
@CrossOrigin(origins = "http://localhost:4200")
public class LicenseController {
    private final LicenseService licenseServiceImpl;

    public LicenseController(LicenseService licenseServiceImpl) {
        this.licenseServiceImpl = licenseServiceImpl;
    }

    // TODO: REMOVE THIS ENDPOINT
    // Create
//    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ResponseStatus(value = OK)
//    public void addLicense(@RequestBody License license) throws InvalidLicenseException, InvalidIdentityException {
//        this.licenseServiceImpl.addLicense(license);
//    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = OK)
    public void addLicense(@RequestBody LicenseJson licenseJson) throws InvalidLicenseException, InvalidIdentityException {
        this.licenseServiceImpl.addLicense(licenseJson);
    }

    // Read
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<License> getAllLicenses() {
        return this.licenseServiceImpl.getAllLicenses();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "ref")
    public License getLicenseByIdentityRef(@RequestBody Map<String, String> identityRef) throws InvalidLicenseException{
        return this.licenseServiceImpl.getLicenseByIdentityRef(identityRef.get("ref"));
    }

    // Update
    @PutMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = OK)
    public void updateLicense(@RequestBody License license) throws InvalidLicenseException {
        this.licenseServiceImpl.updateLicense(license);
    }

    // Delete
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = OK)
    public void deleteLicense(@RequestBody License license) throws InvalidLicenseException {
        this.licenseServiceImpl.removeLicense(license);
    }

}
