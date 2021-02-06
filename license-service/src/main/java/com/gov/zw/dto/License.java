package com.gov.zw.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Licenses")
@Data
public class License {

    @Id
    private String id;
    private String identityRef;
    private String surname;
    private String firstNames;
    private String dateOfBirth;
    private String country;
    private String dateOfIssue;
    private String expiryDate;
    private String agency;
    private String licenseNumber;
    private String signatureImage;
    private String address;

    public License() { }

    public License(String id, String identityRef, String surname, String firstNames, String dateOfBirth, String country, String dateOfIssue, String expiryDate, String agency, String licenseNumber, String signatureImage, String address) {
        this.id = id;
        this.identityRef = identityRef;
        this.surname = surname;
        this.firstNames = firstNames;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.dateOfIssue = dateOfIssue;
        this.expiryDate = expiryDate;
        this.agency = agency;
        this.licenseNumber = licenseNumber;
        this.signatureImage = signatureImage;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getIdentityRef() {
        return identityRef;
    }

    public String getSurname() {
        return surname;
    }

    public String getFirstNames() {
        return firstNames;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getAgency() {
        return agency;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getSignatureImage() {
        return signatureImage;
    }

    public String getAddress() {
        return address;
    }
}
