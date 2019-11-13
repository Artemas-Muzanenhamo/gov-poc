package com.gov.zw.domain;

import com.gov.zw.dto.Identity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IdentityJsonTest {
    private static final String ID = "1";
    private static final String IDENTITY_REF = "1";
    private static final String NAME = "Artemas";
    private static final String SURNAME = "Muzanenhamo";
    private static final String BIRTH_DATE = "28/03/1990";
    private static final String VILLAGE_OF_ORIGIN = "Mashayamombe";
    private static final String PLACE_OF_BIRTH = "Harare";
    private static final String DATE_OF_ISSUE = "17/11/2017";
    private IdentityJson identityJson;

    @Test
    @DisplayName("Both IdentityJson objects should be equal")
    void equalityCheck() {
        identityJson = new IdentityJson();
        IdentityJson newIdentity = new IdentityJson();
        assertThat(identityJson).isEqualTo(newIdentity);
        assertThat(identityJson.toString()).isEqualTo(newIdentity.toString());
        assertThat(identityJson.hashCode()).isEqualTo(newIdentity.hashCode());
    }

    @Test
    @DisplayName("Should produce a valid IdentityJson from a valid IdentityDTO")
    void should_produce_a_valid_identity_json_from_a_valid_identity() {
        Identity identity = new Identity(ID, IDENTITY_REF, NAME, SURNAME, BIRTH_DATE,
                VILLAGE_OF_ORIGIN, PLACE_OF_BIRTH, DATE_OF_ISSUE);
        identityJson = new IdentityJson(identity);
        IdentityJson newIdentity = new IdentityJson(identity);
        assertThat(identityJson).isEqualTo(newIdentity);
    }
}
