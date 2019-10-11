package com.gov.zw.service;

import com.gov.zw.client.Identity;
import com.gov.zw.client.IdentityClient;
import com.gov.zw.client.IdentityReferenceJson;
import com.gov.zw.client.IdentityReferenceJsonMapper;
import com.gov.zw.domain.License;
import com.gov.zw.domain.LicenseJson;
import com.gov.zw.domain.LicenseJsonMapper;
import com.gov.zw.repository.LicenseRepository;
import com.gov.zw.exception.InvalidIdentityException;
import com.gov.zw.exception.InvalidLicenseException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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

    private void addLicense(License license) throws InvalidLicenseException, InvalidIdentityException {
        IdentityReferenceJson identityReferenceJson = Optional.ofNullable(license)
                .map(this::getLicenseIdentityReferenceJsonFunction)
                .orElseThrow(() -> new InvalidLicenseException(THE_LICENSE_IS_INVALID));

        Optional.ofNullable(identityClient.findIdentityByIdReferenceNumber(identityReferenceJson))
                .orElseThrow(() -> new InvalidIdentityException(IDENTITY_IS_INVALID_OR_DOES_NOT_EXIST));

        licenseRepository.save(license);
    }

    @Override
    public void addLicense(LicenseJson licenseJson) throws InvalidIdentityException, InvalidLicenseException {
        License license = licenseJsonMapper.toDto(licenseJson);
        addLicense(license);
    }

    @Override
    public List<LicenseJson> getAllLicenses() {
        return this.licenseRepository.findAll()
                .stream()
                .map(LicenseJson::new)
                .collect(toList());
    }

    @Override
    public void updateLicense(LicenseJson licenseJson) throws InvalidLicenseException {
        License license = licenseJsonMapper.toDto(licenseJson);
        updateLicense(license);
    }

    @Override
    public void removeLicense(LicenseJson licenseJson) throws InvalidLicenseException {
        License license = licenseJsonMapper.toDto(licenseJson);
        removeLicense(license);
    }

    License getLicenseByIdentityRef(String identityRef) throws InvalidLicenseException {
        return Optional.ofNullable(identityRef)
                .map(identityReference -> licenseRepository.findLicenseByIdentityRef(identityReference))
                .orElseThrow((() -> new InvalidLicenseException("License IdRef is not valid")));
    }

    @Override
    public LicenseJson getLicenseByIdentityRef(IdentityReferenceJson identityReferenceJson) throws InvalidLicenseException {
        String IdentityRef = identityReferenceJsonMapper.toIdentityReference(identityReferenceJson);
        License license = getLicenseByIdentityRef(IdentityRef);
        return new LicenseJson(license);
    }

    private IdentityReferenceJson getLicenseIdentityReferenceJsonFunction(License license) {
        return new IdentityReferenceJson(license.getIdentityRef());
    }

    void updateLicense(License license) throws InvalidLicenseException {
        License validLicense = Optional.ofNullable(license)
                .orElseThrow(() -> new InvalidLicenseException(THE_LICENSE_IS_INVALID));
        this.licenseRepository.save(validLicense);
    }

    void removeLicense(License license) throws InvalidLicenseException {
        License validLicense = Optional.ofNullable(license)
                .orElseThrow(() -> new InvalidLicenseException(THE_LICENSE_IS_INVALID));
        this.licenseRepository.delete(validLicense);
    }
}
