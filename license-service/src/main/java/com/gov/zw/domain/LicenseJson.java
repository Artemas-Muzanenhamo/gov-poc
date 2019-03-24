package com.gov.zw.domain;

public class LicenseJson {

    public String id;
    public String identityRef;
    public String surname;
    public String firstNames;
    public String dateOfBirth;
    public String country;
    public String dateOfIssue;
    public String expiryDate;
    public String agency;
    public String licenseNumber;
    public String signatureImage;
    public String address;

    public LicenseJson() {
    }

    public LicenseJson(License license) {
        this.id = license.getId();
        this.identityRef = license.getIdentityRef();
        this.surname = license.getSurname();
        this.firstNames = license.getFirstNames();
        this.dateOfBirth = license.getDateOfBirth();
        this.country = license.getCountry();
        this.dateOfIssue = license.getDateOfIssue();
        this.expiryDate = license.getExpiryDate();
        this.agency = license.getAgency();
        this.licenseNumber = license.getLicenseNumber();
        this.signatureImage = license.getSignatureImage();
        this.address = license.getAddress();
    }
}
