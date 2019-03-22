package com.gov.zw.domain;

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


}
