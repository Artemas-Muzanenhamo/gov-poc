package com.gov.zw.domain;

import java.util.Objects;

public class IdentityJson {

    public String id;
    public String identityRef;
    public String name;
    public String surname;
    public String birthDate;
    public String villageOfOrigin;
    public String placeOfBirth;
    public String dateOfIssue;

    public IdentityJson() {
    }

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
