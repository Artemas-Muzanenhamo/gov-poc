package com.gov.zw.domain;

import com.gov.zw.dto.Identity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IdentityTest {

    private static final String ID = "1";
    private static final String IDENTITY_REFERENCE = "1234AM1";
    private static final String NAME = "Artemas";
    private static final String SURNAME = "Muzanenhamo";
    private static final String BIRTH_DATE = "28/03/1990";
    private static final String VILLAGE_OF_ORIGIN = "Mashayamombe";
    private static final String PLACE_OF_BIRTH = "Harare";
    private static final String DATE_OF_ISSUE = "22/11/2017";
    private Identity identity;
    private Identity identity2;

    @BeforeEach
    void init() {
        identity = new Identity(
                ID, IDENTITY_REFERENCE, NAME, SURNAME, BIRTH_DATE, VILLAGE_OF_ORIGIN,
                PLACE_OF_BIRTH, DATE_OF_ISSUE);

        identity2 = new Identity();
    }

    @Test
    @DisplayName("Populated Identity object")
    void creation() {
        assertThat(identity.getId()).isEqualTo(ID);
        assertThat(identity.getIdentityRef()).isEqualTo(IDENTITY_REFERENCE);
        assertThat(identity.getName()).isEqualTo(NAME);
        assertThat(identity.getSurname()).isEqualTo(SURNAME);
        assertThat(identity.getBirthDate()).isEqualTo(BIRTH_DATE);
        assertThat(identity.getVillageOfOrigin()).isEqualTo(VILLAGE_OF_ORIGIN);
        assertThat(identity.getPlaceOfBirth()).isEqualTo(PLACE_OF_BIRTH);
        assertThat(identity.getDateOfIssue()).isEqualTo(DATE_OF_ISSUE);
    }

    @Test
    @DisplayName("Should return equality of two Identity objects without arguments")
    void noArgsEquals() {
        Identity expectedId = new Identity();

        assertThat(expectedId).isEqualTo(identity2);
    }

    @Test
    @DisplayName("Should return no equality of two non matching Identity objects")
    void noArgsNotEquals() {
        Identity expectedId = new Identity();

        assertThat(expectedId).isNotEqualTo(identity);
    }

    @Test
    @DisplayName("Should return Identity as not null")
    void noArgsNotEqualToNull() {
        assertThat(identity2).isNotNull();
    }

    @Test
    @DisplayName("Should return Identity as null")
    void noArgsEqualsToNull(){
        Identity identity = null;

        assertThat(identity).isNull();
    }

    @Test
    @DisplayName("Should return no equality from two non matching Identity objects' HashCodes")
    void hashCodeNotEquals() {
        Identity expectedId = new Identity();

        assertThat(expectedId.hashCode()).isNotEqualTo(identity.hashCode());
    }

    @Test
    @DisplayName("Should return equality from two matching Identity objects' HashCodes")
    void hashCodeEquals() {
        Identity expectedId = identity;

        assertThat(expectedId.hashCode()).isEqualTo(identity.hashCode());
    }

    @Test
    @DisplayName("Should return not null from Identity objects' Hashcode")
    void hashCodeNotEqualToNull() {
        assertThat(identity2.hashCode()).isNotNull();
    }

    @Test
    @DisplayName("Should return null from Identity objects' Hashcode")
    void hashCodeIsEqualsToNull(){
        Identity identity = null;

        assertThrows(NullPointerException.class, () -> identity.hashCode());
    }

    @Test
    @DisplayName("Should return no equality from two non matching Identity objects' toString()")
    void toStringNotEquals() {
        Identity expectedId = new Identity();
        assertThat(expectedId.toString()).isNotEqualTo(identity.toString());
    }

    @Test
    @DisplayName("Should return equality from two matching Identity objects' toString()")
    void toStringEquals() {
        Identity expectedId = new Identity();
        assertThat(expectedId.toString()).isEqualTo(identity2.toString());
    }


}
