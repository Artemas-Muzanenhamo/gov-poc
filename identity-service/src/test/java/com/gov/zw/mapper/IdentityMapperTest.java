package com.gov.zw.mapper;

import com.gov.zw.domain.Identity;
import com.gov.zw.json.IdentityJson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.gov.zw.mapper.IdentityMapper.toIdentityDTO;
import static com.gov.zw.mapper.IdentityMapper.toIdentityJson;
import static org.assertj.core.api.Assertions.assertThat;

class IdentityMapperTest {
    private static final String ID = "1";
    private static final String IDENTITY_REF = "MUZAN1234";
    private static final String NAME = "Artemas";
    private static final String SURNAME = "Muzanenhamo";
    private static final String BIRTH_DATE = "28/03/1990";
    private static final String VILLAGE_OF_ORIGIN = "Mashayamombe";
    private static final String PLACE_OF_BIRTH = "Harare";
    private static final String DATE_OF_ISSUE = "22/01/2018";

    @Test
    @DisplayName("Should map IdentityJson to Identity")
    void mapIdentityJsonToDTO() {
        IdentityJson identityJson = new IdentityJson(ID, IDENTITY_REF, NAME, SURNAME, BIRTH_DATE, VILLAGE_OF_ORIGIN, PLACE_OF_BIRTH, DATE_OF_ISSUE);

        Identity identity = toIdentityDTO(identityJson);

        assertThat(identity).isNotNull();
        assertThat(identity.id()).isEqualTo(ID);
        assertThat(identity.identityRef()).isEqualTo(IDENTITY_REF);
        assertThat(identity.name()).isEqualTo(NAME);
        assertThat(identity.surname()).isEqualTo(SURNAME);
        assertThat(identity.birthDate()).isEqualTo(BIRTH_DATE);
        assertThat(identity.villageOfOrigin()).isEqualTo(VILLAGE_OF_ORIGIN);
        assertThat(identity.placeOfBirth()).isEqualTo(PLACE_OF_BIRTH);
        assertThat(identity.dateOfIssue()).isEqualTo(DATE_OF_ISSUE);
    }

    @Test
    @DisplayName("Should map IdentityDTO to IdentityJson")
    void mapIdentityDTOToJson() {
        Identity identity = new Identity(ID, IDENTITY_REF, NAME, SURNAME, BIRTH_DATE, VILLAGE_OF_ORIGIN, PLACE_OF_BIRTH, DATE_OF_ISSUE);

        IdentityJson identityJson = toIdentityJson(identity);

        assertThat(identityJson).isNotNull();
        assertThat(identityJson.id()).isEqualTo(ID);
        assertThat(identityJson.identityRef()).isEqualTo(IDENTITY_REF);
        assertThat(identityJson.name()).isEqualTo(NAME);
        assertThat(identityJson.surname()).isEqualTo(SURNAME);
        assertThat(identityJson.birthDate()).isEqualTo(BIRTH_DATE);
        assertThat(identityJson.villageOfOrigin()).isEqualTo(VILLAGE_OF_ORIGIN);
        assertThat(identityJson.placeOfBirth()).isEqualTo(PLACE_OF_BIRTH);
        assertThat(identityJson.dateOfIssue()).isEqualTo(DATE_OF_ISSUE);
    }

    @Test
    @DisplayName("Should return empty IdentityJson when IdentityDTO is null")
    void returnEmptyIdentityJsonToDTO() {
        IdentityJson identityJson = toIdentityJson(null);

        assertThat(identityJson).isNotNull();
    }
}
