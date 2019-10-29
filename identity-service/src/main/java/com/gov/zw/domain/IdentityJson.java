package com.gov.zw.domain;

import com.gov.zw.dto.Identity;

import java.util.Objects;

public class IdentityJson {

    private String id;
    private String identityRef;
    private String name;
    private String surname;
    private String birthDate;
    private String villageOfOrigin;
    private String placeOfBirth;
    private String dateOfIssue;

    public IdentityJson() { }

    public IdentityJson(Identity identity) {
        this.id = identity.getId();
        this.identityRef = identity.getIdentityRef();
        this.name = identity.getName();
        this.surname = identity.getSurname();
        this.birthDate = identity.getBirthDate();
        this.villageOfOrigin = identity.getVillageOfOrigin();
        this.placeOfBirth = identity.getPlaceOfBirth();
        this.dateOfIssue = identity.getDateOfIssue();
    }

    public IdentityJson(String id, String identityRef, String name, String surname, String birthDate, String villageOfOrigin, String placeOfBirth, String dateOfIssue) {
        this.id = id;
        this.identityRef = identityRef;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.villageOfOrigin = villageOfOrigin;
        this.placeOfBirth = placeOfBirth;
        this.dateOfIssue = dateOfIssue;
    }

    public String getId() {
        return id;
    }

    public String getIdentityRef() {
        return identityRef;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getVillageOfOrigin() {
        return villageOfOrigin;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentityJson that = (IdentityJson) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(identityRef, that.identityRef) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(villageOfOrigin, that.villageOfOrigin) &&
                Objects.equals(placeOfBirth, that.placeOfBirth) &&
                Objects.equals(dateOfIssue, that.dateOfIssue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identityRef, name, surname, birthDate, villageOfOrigin, placeOfBirth, dateOfIssue);
    }

    @Override
    public String toString() {
        return "IdentityJson{" +
                "id='" + id + '\'' +
                ", identityRef='" + identityRef + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", villageOfOrigin='" + villageOfOrigin + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", dateOfIssue='" + dateOfIssue + '\'' +
                '}';
    }
}
