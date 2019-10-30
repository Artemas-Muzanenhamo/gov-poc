package com.gov.zw.mapper;

import com.gov.zw.dto.License;
import com.gov.zw.domain.LicenseJson;
import org.springframework.stereotype.Component;

@Component
public class LicenseJsonMapper {
    public static License toLicenseDTO(LicenseJson json) {
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
