package com.gov.zw.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "Licenses")
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

    License() {
        //JPA Why?...
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        License license = (License) o;
        return Objects.equals(id, license.id) &&
                Objects.equals(identityRef, license.identityRef) &&
                Objects.equals(surname, license.surname) &&
                Objects.equals(firstNames, license.firstNames) &&
                Objects.equals(dateOfBirth, license.dateOfBirth) &&
                Objects.equals(country, license.country) &&
                Objects.equals(dateOfIssue, license.dateOfIssue) &&
                Objects.equals(expiryDate, license.expiryDate) &&
                Objects.equals(agency, license.agency) &&
                Objects.equals(licenseNumber, license.licenseNumber) &&
                Objects.equals(signatureImage, license.signatureImage) &&
                Objects.equals(address, license.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identityRef, surname, firstNames, dateOfBirth, country, dateOfIssue, expiryDate, agency, licenseNumber, signatureImage, address);
    }
}
