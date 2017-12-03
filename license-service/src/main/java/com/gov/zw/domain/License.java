package com.gov.zw.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Licenses")
public class License {

    @Id
    private String id;
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

    public License(String id, String surname, String firstNames, String dateOfBirth, String country, String dateOfIssue, String expiryDate, String agency, String licenseNumber, String signatureImage, String address) {
        this.id = id;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstNames() {
        return firstNames;
    }

    public void setFirstNames(String firstNames) {
        this.firstNames = firstNames;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getSignatureImage() {
        return signatureImage;
    }

    public void setSignatureImage(String signatureImage) {
        this.signatureImage = signatureImage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
