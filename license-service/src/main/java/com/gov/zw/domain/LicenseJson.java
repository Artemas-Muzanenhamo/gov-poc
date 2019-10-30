package com.gov.zw.domain;

import com.gov.zw.dto.License;

import java.util.Objects;

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

    public LicenseJson() { }

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
