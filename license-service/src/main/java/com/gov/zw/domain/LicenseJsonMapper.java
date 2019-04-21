package com.gov.zw.domain;

import org.springframework.stereotype.Component;

@Component
public class LicenseJsonMapper {
    public License toDto(LicenseJson json) {
        return new License(
                json.id,
                json.identityRef,
                json.surname,
                json.firstNames,
                json.dateOfBirth,
                json.country,
                json.dateOfIssue,
                json.expiryDate,
                json.agency,
                json.licenseNumber,
                json.signatureImage,
                json.address
        );
    }
}