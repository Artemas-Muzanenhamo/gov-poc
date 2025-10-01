package com.gov.zw.json;

public record LicenseJson(
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
    public static LicenseJson empty() {
        return new LicenseJson(null, // id
                null, // identityRef
                null, // surname
                null, // firstNames
                null, // dateOfBirth
                null, // country
                null, //dateOfIssue
                null, //expiryDate
                null, // agency
                null, //licenseNumber
                null, // signatureImage
                null // address
        );
    }
}
