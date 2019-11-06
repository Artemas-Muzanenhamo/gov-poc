package com.gov.zw.service;

import com.gov.zw.client.IdentityClient;
import com.gov.zw.client.IdentityReferenceJson;
import com.gov.zw.client.IdentityReferenceJsonMapper;
import com.gov.zw.client.dto.IdentityReference;
import com.gov.zw.dto.License;
import com.gov.zw.domain.LicenseJson;
import com.gov.zw.mapper.LicenseMapper;
import com.gov.zw.exception.InvalidIdentityException;
import com.gov.zw.exception.InvalidLicenseException;
import com.gov.zw.repository.LicenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LicenseServiceImpl implements LicenseService {

    private static final String THE_LICENSE_IS_INVALID = "The license is invalid!";
    private static final String IDENTITY_IS_INVALID_OR_DOES_NOT_EXIST = "Identity is invalid or does not exist!";

    private IdentityClient identityClient;
    private LicenseRepository licenseRepository;

    public LicenseServiceImpl(IdentityClient identityClient, LicenseRepository licenseRepository,
                              LicenseMapper licenseMapper) {
        this.identityClient = identityClient;
        this.licenseRepository = licenseRepository;
    }

    @Override
    public void addLicense(License license) throws InvalidIdentityException, InvalidLicenseException {
        IdentityReferenceJson identityReferenceJson = Optional.ofNullable(license)
                .filter(licenseDto -> Objects.nonNull(licenseDto.getIdentityRef()))
                .map(this::getLicenseIdentityReferenceJson)
                .orElseThrow(() -> new InvalidLicenseException(THE_LICENSE_IS_INVALID));

        Optional.of(identityReferenceJson)
                .map(idReferenceJson -> identityClient.findIdentityByIdReferenceNumber(idReferenceJson))
                .orElseThrow(() -> new InvalidIdentityException(IDENTITY_IS_INVALID_OR_DOES_NOT_EXIST));

        licenseRepository.save(license);
    }

    @Override
    public List<License> getAllLicenses() {
        return this.licenseRepository.findAll();
    }

    @Override
    public void updateLicense(License license) throws InvalidLicenseException {
        License validLicense = Optional.ofNullable(license)
                .orElseThrow(() -> new InvalidLicenseException(THE_LICENSE_IS_INVALID));
        this.licenseRepository.save(validLicense);
    }

    @Override
    public void removeLicense(LicenseJson licenseJson) throws InvalidLicenseException {
        License license = LicenseMapper.toLicenseDTO(licenseJson);
        removeLicense(license);
    }

    @Override
    public License getLicenseByIdentityRef(IdentityReference identityReference) throws InvalidLicenseException {
        return Optional.ofNullable(identityReference)
                .map(IdentityReference::getIdRef)
                .map(e -> licenseRepository.findLicenseByIdentityRef(e))
                .orElseThrow((() -> new InvalidLicenseException("License IdRef is not valid")));
    }

    private IdentityReferenceJson getLicenseIdentityReferenceJson(License license) {
        return new IdentityReferenceJson(license.getIdentityRef());
    }

    void removeLicense(License license) throws InvalidLicenseException {
        License validLicense = Optional.ofNullable(license)
                .orElseThrow(() -> new InvalidLicenseException(THE_LICENSE_IS_INVALID));
        this.licenseRepository.delete(validLicense);
    }
}
