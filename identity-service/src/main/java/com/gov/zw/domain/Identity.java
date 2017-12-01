package com.gov.zw.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by Artemas on 18/11/2017.
 */
@Document(collection = "Identities")
public class Identity {

    @Id
    private String idNumber;
    private String name;
    private String surname;
    private String birthDate;
    private String villageOfOrigin;
    private String placeOfBirth;
    private String dateOfIssue;

    Identity(){
        // Why JPA Why ?!!!
    }

    public Identity(String idNumber, String name, String surname, String birthDate, String villageOfOrigin, String placeOfBirth, String dateOfIssue) {
        this.idNumber = idNumber;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.villageOfOrigin = villageOfOrigin;
        this.placeOfBirth = placeOfBirth;
        this.dateOfIssue = dateOfIssue;
    }

    public String getIdNumber() {
        return idNumber;
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
