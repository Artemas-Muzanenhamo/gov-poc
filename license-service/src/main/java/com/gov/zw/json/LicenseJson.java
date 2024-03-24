package com.gov.zw.json;

import java.util.Objects;

public class LicenseJson {

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

    public LicenseJson() { }

    public LicenseJson(String id, String identityRef, String surname, String firstNames, String dateOfBirth, String country, String dateOfIssue, String expiryDate, String agency, String licenseNumber, String signatureImage, String address) {
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

    @Override
    public String toString() {
        return "LicenseJson{" +
                "id='" + id + '\'' +
                ", identityRef='" + identityRef + '\'' +
                ", surname='" + surname + '\'' +
                ", firstNames='" + firstNames + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", country='" + country + '\'' +
                ", dateOfIssue='" + dateOfIssue + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", agency='" + agency + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", signatureImage='" + signatureImage + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LicenseJson that = (LicenseJson) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(identityRef, that.identityRef) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(firstNames, that.firstNames) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(country, that.country) &&
                Objects.equals(dateOfIssue, that.dateOfIssue) &&
                Objects.equals(expiryDate, that.expiryDate) &&
                Objects.equals(agency, that.agency) &&
                Objects.equals(licenseNumber, that.licenseNumber) &&
                Objects.equals(signatureImage, that.signatureImage) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identityRef, surname, firstNames, dateOfBirth, country, dateOfIssue, expiryDate, agency, licenseNumber, signatureImage, address);
    }
}
