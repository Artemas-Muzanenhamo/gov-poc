package com.gov.zw.service;

import com.gov.zw.client.*;
import com.gov.zw.domain.License;
import com.gov.zw.domain.LicenseJson;
import com.gov.zw.domain.LicenseJsonMapper;
import com.gov.zw.repository.LicenseRepository;
import com.gov.zw.util.InvalidIdentityException;
import com.gov.zw.util.InvalidLicenseException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class LicenseServiceImpl implements LicenseService {

    private static final String THE_LICENSE_IS_INVALID = "The license is invalid!";
    private static final String IDENTITY_IS_INVALID_OR_DOES_NOT_EXIST = "Identity is invalid or does not exist!";

    private IdentityClient identityClient;
    private LicenseRepository licenseRepository;
    private LicenseJsonMapper licenseJsonMapper;
    private IdentityReferenceJsonMapper identityReferenceJsonMapper;

    public LicenseServiceImpl(IdentityClient identityClient, LicenseRepository licenseRepository,
                              LicenseJsonMapper licenseJsonMapper,
                              IdentityReferenceJsonMapper identityReferenceJsonMapper) {
        this.identityClient = identityClient;
        this.licenseRepository = licenseRepository;
        this.licenseJsonMapper = licenseJsonMapper;
        this.identityReferenceJsonMapper = identityReferenceJsonMapper;
    }

    void addLicense(License license) throws InvalidLicenseException, InvalidIdentityException {
        Optional<License> licenseOptional = Optional.ofNullable(license);
        Map<String, String> referenceNumber = new HashMap<>();
        referenceNumber.put("idRef", licenseOptional
                .orElseThrow(() -> new InvalidLicenseException(THE_LICENSE_IS_INVALID)).getIdentityRef());
        IdentityReferenceJson identityReferenceJson = new IdentityReferenceJson(referenceNumber);
        Optional<Identity> identityOptional = Optional.ofNullable(identityClient.findIdentityByIdReferenceNumber(identityReferenceJson));
        if (identityOptional.isPresent()) {
            licenseRepository.save(license);
        } else {
            throw new InvalidIdentityException(IDENTITY_IS_INVALID_OR_DOES_NOT_EXIST);
        }
    }

    @Override
    public void addLicense(LicenseJson licenseJson) throws InvalidIdentityException, InvalidLicenseException {
        License license = licenseJsonMapper.toDto(licenseJson);
        addLicense(license);
    }

    @Override
    public List<LicenseJson> getAllLicenses() {
        List<License> licenses = this.licenseRepository.findAll();
        return licenses.stream().map(LicenseJson::new).collect(toList());
    }

    void updateLicense(License license) throws InvalidLicenseException {
        Optional<License> licenseOptional = Optional.ofNullable(license);
        this.licenseRepository.save(licenseOptional
                .orElseThrow(() -> new InvalidLicenseException(THE_LICENSE_IS_INVALID)));
    }

    @Override
    public void updateLicense(LicenseJson licenseJson) throws InvalidLicenseException {
        License license = licenseJsonMapper.toDto(licenseJson);
        updateLicense(license);
    }

    void removeLicense(License license) throws InvalidLicenseException {
        Optional<License> licenseOptional = Optional.ofNullable(license);
        this.licenseRepository.delete(licenseOptional
                .orElseThrow(() -> new InvalidLicenseException(THE_LICENSE_IS_INVALID)));
    }

    @Override
    public void removeLicense(LicenseJson licenseJson) throws InvalidLicenseException {
        License license = licenseJsonMapper.toDto(licenseJson);
        removeLicense(license);
    }

    License getLicenseByIdentityRef(String identityRef) throws InvalidLicenseException {
        Optional<String> identityRefOptional = Optional.ofNullable(identityRef);
        return this.licenseRepository.findLicenseByIdentityRef(identityRefOptional
                .orElseThrow((() -> new InvalidLicenseException("License IdRef is not valid"))));
    }

    @Override
    public LicenseJson getLicenseByIdentityRef(IdentityReferenceJson identityReferenceJson) throws InvalidLicenseException {
        String IdentityRef = identityReferenceJsonMapper.toIdentityReference(identityReferenceJson);
        License license = getLicenseByIdentityRef(IdentityRef);
        return new LicenseJson(license);
    }
}
