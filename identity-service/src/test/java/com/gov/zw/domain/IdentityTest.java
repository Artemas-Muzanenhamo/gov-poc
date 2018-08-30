package com.gov.zw.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IdentityTest {

    private Identity identity;
    private Identity identity2;

    @BeforeEach
    void init() {
        identity = new Identity(
                "1", "1234AM1", "Artemas", "Muzanenhamo", "28/03/1990", "Mashayamombe",
                "Harare", "22/11/2017");

        identity2 = new Identity();
    }

    @Test
    void creation() {
        assertThat(identity.getId()).isEqualTo("1");
        assertThat(identity.getIdentityRef()).isEqualTo("1234AM1");
        assertThat(identity.getName()).isEqualTo("Artemas");
        assertThat(identity.getSurname()).isEqualTo("Muzanenhamo");
        assertThat(identity.getBirthDate()).isEqualTo("28/03/1990");
        assertThat(identity.getVillageOfOrigin()).isEqualTo("Mashayamombe");
        assertThat(identity.getPlaceOfBirth()).isEqualTo("Harare");
        assertThat(identity.getDateOfIssue()).isEqualTo("22/11/2017");
    }

    @Test
    void noArgsEquals() {
        Identity expectedId = new Identity();
        assertThat(expectedId).isEqualTo(identity2);
    }

    @Test
    void noArgsNotEquals() {
        Identity expectedId = new Identity();
        assertThat(expectedId).isNotEqualTo(identity);
    }

    @Test
    void noArgsNotEqualToNull() {
        assertThat(identity2).isNotNull();
    }

    @Test
    void hashCodeNotEquals() {
        Identity expectedId = new Identity();
        assertThat(expectedId.hashCode()).isNotEqualTo(identity.hashCode());
    }

    @Test
    void hashCodeEquals() {
        Identity expectedId = identity;
        assertThat(expectedId.hashCode()).isEqualTo(identity.hashCode());
    }

    @Test
    void hashCodeNotEqualToNull() {
        assertThat(identity2.hashCode()).isNotNull();
    }

    @Test
    void toStringNotEquals() {
        Identity expectedId = new Identity();
        assertThat(expectedId.toString()).isNotEqualTo(identity.toString());
    }

    @Test
    void toStringEquals() {
        Identity expectedId = new Identity();
        assertThat(expectedId.toString()).isEqualTo(identity2.toString());
    }


}
