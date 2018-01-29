package com.gov.zw.client;

import java.util.Objects;

public class Identity {

    private String id;
    private String identityRef;
    private String name;
    private String surname;
    private String birthDate;
    private String villageOfOrigin;
    private String placeOfBirth;
    private String dateOfIssue;

    public Identity(){}

    public Identity(String id, String identityRef, String name, String surname, String birthDate, String villageOfOrigin, String placeOfBirth, String dateOfIssue) {
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
        if (!(o instanceof Identity)) return false;
        Identity identity = (Identity) o;
        return Objects.equals(id, identity.id) &&
                Objects.equals(identityRef, identity.identityRef) &&
                Objects.equals(name, identity.name) &&
                Objects.equals(surname, identity.surname) &&
                Objects.equals(birthDate, identity.birthDate) &&
                Objects.equals(villageOfOrigin, identity.villageOfOrigin) &&
                Objects.equals(placeOfBirth, identity.placeOfBirth) &&
                Objects.equals(dateOfIssue, identity.dateOfIssue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identityRef, name, surname, birthDate, villageOfOrigin, placeOfBirth, dateOfIssue);
    }
}
