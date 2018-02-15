package com.gov.zw.service;

import com.gov.zw.client.Identity;
import com.gov.zw.client.IdentityClient;
import com.gov.zw.domain.License;
import com.gov.zw.repository.LicenseRepository;
import com.gov.zw.util.IdentityInvalidException;
import com.gov.zw.util.InvalidLicenseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LicenseServiceImpl implements LicenseService {

    private static Logger logger = LoggerFactory.getLogger(LicenseServiceImpl.class);

    private IdentityClient identityClient;

    private LicenseRepository licenseRepository;

    public LicenseServiceImpl(IdentityClient identityClient, LicenseRepository licenseRepository){
        this.identityClient = identityClient;
        this.licenseRepository = licenseRepository;
    }

    @Override
    public void addLicense(License license) throws IdentityInvalidException {
        Map<String, String> referenceNumber = new HashMap<>();
        referenceNumber.put("idRef", license.getIdentityRef());
        logger.debug("Reference Number passed is: " + referenceNumber.get("idRef"));
        Optional<Identity> identityOptional = Optional.ofNullable(identityClient.findIdentityByIdReferenceNumber(referenceNumber));

        if (identityOptional.isPresent()){
            licenseRepository.save(license);
        }else {
            throw new IdentityInvalidException("Identity is invalid or does not exist!");
        }
    }

    @Override
    public List<License> getAllLicenses() {
        return this.licenseRepository.findAll();
    }

    @Override
    public void updateLicense(License license) throws InvalidLicenseException {
        Optional<License> licenseOptional = Optional.ofNullable(license);
        if (licenseOptional.isPresent()){
            this.licenseRepository.save(license);
        } else {
            throw new InvalidLicenseException("The license is invalid!");
        }
    }

    @Override
    public void removeLicense(License license) throws InvalidLicenseException {
        Optional<License> licenseOptional = Optional.ofNullable(license);
        if (licenseOptional.isPresent()) {
            this.licenseRepository.delete(license);
        } else {
            throw new InvalidLicenseException("The license is invalid!");
        }
    }
}
