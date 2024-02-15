package com.gov.zw.json;

import lombok.Getter;

import java.util.Objects;

@Getter
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
