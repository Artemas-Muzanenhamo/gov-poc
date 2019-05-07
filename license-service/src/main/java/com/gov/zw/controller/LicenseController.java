package com.gov.zw.controller;

import com.gov.zw.client.IdentityReferenceJson;
import com.gov.zw.domain.LicenseJson;
import com.gov.zw.service.LicenseService;
import com.gov.zw.util.InvalidIdentityException;
import com.gov.zw.util.InvalidLicenseException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/licenses")
@CrossOrigin(origins = "http://localhost:4200")
public class LicenseController {
    private final LicenseService licenseServiceImpl;

    public LicenseController(LicenseService licenseServiceImpl) {
        this.licenseServiceImpl = licenseServiceImpl;
    }

    @PostMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = OK)
    public void addLicense(@RequestBody LicenseJson licenseJson) throws InvalidLicenseException, InvalidIdentityException {
        this.licenseServiceImpl.addLicense(licenseJson);
    }

    // Read
    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public List<LicenseJson> getAllLicenses() {
        return this.licenseServiceImpl.getAllLicenses();
    }

    @PostMapping(produces = APPLICATION_JSON_UTF8_VALUE, value = "ref")
    public LicenseJson getLicenseByIdentityRef(@RequestBody IdentityReferenceJson identityReferenceJson) throws InvalidLicenseException {
        return this.licenseServiceImpl.getLicenseByIdentityRef(identityReferenceJson);
    }

    // Update
    @PutMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = OK)
    public void updateLicense(@RequestBody LicenseJson licenseJson) throws InvalidLicenseException {
        this.licenseServiceImpl.updateLicense(licenseJson);
    }

    // Delete
    @DeleteMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = OK)
    public void deleteLicense(@RequestBody LicenseJson licenseJson) throws InvalidLicenseException {
        this.licenseServiceImpl.removeLicense(licenseJson);
    }

}
