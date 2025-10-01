package com.gov.zw.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Licenses")
public record License(
        @Id
        String id,
        String identityRef,
        String surname,
        String firstNames,
        String dateOfBirth,
        String country,
        String dateOfIssue,
        String expiryDate,
        String agency,
        String licenseNumber,
        String signatureImage,
        String address
) {
    public static License empty() {
        return new License(null, // id
                null, // identityRef
                null, // surname
                null, // firstNames
                null, // dateOfBirth
                null, // country
                null, // dateOfIssue
                null, // expiryDate
                null, // agency
                null, // licenseNumber
                null, // signatureImage
                null // address
        );
    }
}
