package com.gov.zw.mapper;

import com.gov.zw.dto.License;
import com.gov.zw.domain.LicenseJson;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LicenseJsonMapper {
    public static License toLicenseDTO(LicenseJson json) {
        return Optional.ofNullable(json)
                .map(licenseJson -> new License(
                        licenseJson.id,
                        licenseJson.identityRef,
                        licenseJson.surname,
                        licenseJson.firstNames,
                        licenseJson.dateOfBirth,
                        licenseJson.country,
                        licenseJson.dateOfIssue,
                        licenseJson.expiryDate,
                        licenseJson.agency,
                        licenseJson.licenseNumber,
                        licenseJson.signatureImage,
                        licenseJson.address))
                .orElse(new License());
    }
}
