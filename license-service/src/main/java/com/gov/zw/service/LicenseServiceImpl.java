package com.gov.zw.service;

import com.gov.zw.client.IdentityClient;
import com.gov.zw.client.IdentityReferenceJson;
import com.gov.zw.client.IdentityReferenceJsonMapper;
import com.gov.zw.dto.License;
import com.gov.zw.domain.LicenseJson;
import com.gov.zw.mapper.LicenseJsonMapper;
import com.gov.zw.exception.InvalidIdentityException;
import com.gov.zw.exception.InvalidLicenseException;
import com.gov.zw.repository.LicenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class LicenseServiceImpl implements LicenseService {

    private static final String THE_LICENSE_IS_INVALID = "The license is invalid!";
    private static final String IDENTITY_IS_INVALID_OR_DOES_NOT_EXIST = "Identity is invalid or does not exist!";

    private IdentityClient identityClient;
    private LicenseRepository licenseRepository;
    private IdentityReferenceJsonMapper identityReferenceJsonMapper;

    public LicenseServiceImpl(IdentityClient identityClient, LicenseRepository licenseRepository,
                              LicenseJsonMapper licenseJsonMapper,
                              IdentityReferenceJsonMapper identityReferenceJsonMapper) {
        this.identityClient = identityClient;
        this.licenseRepository = licenseRepository;
        this.identityReferenceJsonMapper = identityReferenceJsonMapper;
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
    public List<LicenseJson> getAllLicenses() {
        return this.licenseRepository.findAll()
                .stream()
                .map(LicenseJson::new)
                .collect(toList());
    }

    @Override
    public void updateLicense(LicenseJson licenseJson) throws InvalidLicenseException {
        License license = LicenseJsonMapper.toLicenseDTO(licenseJson);
        updateLicense(license);
    }

    @Override
    public void removeLicense(LicenseJson licenseJson) throws InvalidLicenseException {
        License license = LicenseJsonMapper.toLicenseDTO(licenseJson);
        removeLicense(license);
    }

    @Override
    public LicenseJson getLicenseByIdentityRef(IdentityReferenceJson identityReferenceJson) throws InvalidLicenseException {
        String IdentityRef = identityReferenceJsonMapper.toIdentityReference(identityReferenceJson);
        License license = getLicenseByIdentityRef(IdentityRef);
        return new LicenseJson(license);
    }

    License getLicenseByIdentityRef(String identityRef) throws InvalidLicenseException {
        return Optional.ofNullable(identityRef)
                .map(identityReference -> licenseRepository.findLicenseByIdentityRef(identityReference))
                .orElseThrow((() -> new InvalidLicenseException("License IdRef is not valid")));
    }

    private IdentityReferenceJson getLicenseIdentityReferenceJson(License license) {
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
