package com.gov.zw.mapper;

import com.gov.zw.domain.Identity;
import com.gov.zw.json.IdentityJson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.gov.zw.mapper.IdentityListMapper.toIdentitiesJson;
import static org.assertj.core.api.Assertions.assertThat;

class IdentityListMapperTest {

    private static final String ID = "1";
    private static final String IDENTITY_REF = "MUZAN1234";
    private static final String NAME = "Artemas";
    private static final String SURNAME = "Muzanenhamo";
    private static final String BIRTH_DATE = "28/03/1990";
    private static final String VILLAGE_OF_ORIGIN = "Mashayamombe";
    private static final String PLACE_OF_BIRTH = "Harare";
    private static final String DATE_OF_ISSUE = "22/01/2018";

    @Test
    @DisplayName("Should map List of Identity to List of IdentityJson")
    void mapIdentityListToJsonList() {
        Identity identity = new Identity(ID, IDENTITY_REF, NAME, SURNAME, BIRTH_DATE, VILLAGE_OF_ORIGIN, PLACE_OF_BIRTH, DATE_OF_ISSUE);
        List<Identity> identities = new ArrayList<>();
        identities.add(identity);

        List<IdentityJson> identityJsonList = toIdentitiesJson(identities);

        assertThat(identityJsonList).isNotEmpty();
        IdentityJson identityJson = identityJsonList.get(0);
        assertThat(identityJson).isNotNull();
        assertThat(identityJson.getId()).isEqualTo(ID);
        assertThat(identityJson.getIdentityRef()).isEqualTo(IDENTITY_REF);
        assertThat(identityJson.getName()).isEqualTo(NAME);
        assertThat(identityJson.getSurname()).isEqualTo(SURNAME);
        assertThat(identityJson.getBirthDate()).isEqualTo(BIRTH_DATE);
        assertThat(identityJson.getVillageOfOrigin()).isEqualTo(VILLAGE_OF_ORIGIN);
        assertThat(identityJson.getPlaceOfBirth()).isEqualTo(PLACE_OF_BIRTH);
        assertThat(identityJson.getDateOfIssue()).isEqualTo(DATE_OF_ISSUE);
    }

    @Test
    @DisplayName("Should return an empty List of IdentityJson when List of Identity is null")
    void mapToEmptyListOfIdentityJson() {
        List<IdentityJson> identityJsonList = toIdentitiesJson(null);

        assertThat(identityJsonList).isEmpty();
    }
}
