package com.gov.zw.controller;

import com.gov.zw.client.IdentityReferenceJson;
import com.gov.zw.client.dto.IdentityReference;
import com.gov.zw.domain.LicenseJson;
import com.gov.zw.dto.License;
import com.gov.zw.service.LicenseService;
import com.gov.zw.exception.InvalidIdentityException;
import com.gov.zw.exception.InvalidLicenseException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gov.zw.mapper.IdentityReferenceMapper.toIdentityReferenceDTO;
import static com.gov.zw.mapper.LicenseMapper.*;
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
        License license = toLicenseDTO(licenseJson);
        this.licenseServiceImpl.addLicense(license);
    }

    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public List<LicenseJson> getAllLicenses() {
        List<License> licenses = this.licenseServiceImpl.getAllLicenses();
        return toLicenseJsonList(licenses);
    }

    @PostMapping(produces = APPLICATION_JSON_UTF8_VALUE, value = "ref")
    public LicenseJson getLicenseByIdentityRef(@RequestBody IdentityReferenceJson identityReferenceJson) throws InvalidLicenseException {
        IdentityReference identityReference = toIdentityReferenceDTO(identityReferenceJson);
        License license = this.licenseServiceImpl.getLicenseByIdentityRef(identityReference);
        return toLicenseJson(license);
    }

    @PutMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = OK)
    public void updateLicense(@RequestBody LicenseJson licenseJson) throws InvalidLicenseException {
        License license = toLicenseDTO(licenseJson);
        this.licenseServiceImpl.updateLicense(license);
    }

    @DeleteMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = OK)
    public void deleteLicense(@RequestBody LicenseJson licenseJson) throws InvalidLicenseException {
        License license = toLicenseDTO(licenseJson);
        this.licenseServiceImpl.removeLicense(license);
    }

}
