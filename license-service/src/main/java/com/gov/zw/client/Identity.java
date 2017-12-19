package com.gov.zw.client;

public class Identity {

    private String id;
    private String identityRef;
    private String name;
    private String surname;
    private String birthDate;
    private String villageOfOrigin;
    private String placeOfBirth;
    private String dateOfIssue;

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

}
