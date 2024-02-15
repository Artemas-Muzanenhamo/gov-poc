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
        assertThat(identity.getId()).isEqualTo(ID);
        assertThat(identity.getIdentityRef()).isEqualTo(IDENTITY_REF);
        assertThat(identity.getName()).isEqualTo(NAME);
        assertThat(identity.getSurname()).isEqualTo(SURNAME);
        assertThat(identity.getBirthDate()).isEqualTo(BIRTH_DATE);
        assertThat(identity.getVillageOfOrigin()).isEqualTo(VILLAGE_OF_ORIGIN);
        assertThat(identity.getPlaceOfBirth()).isEqualTo(PLACE_OF_BIRTH);
        assertThat(identity.getDateOfIssue()).isEqualTo(DATE_OF_ISSUE);
    }

    @Test
    @DisplayName("Should map IdentityDTO to IdentityJson")
    void mapIdentityDTOToJson() {
        Identity identity = new Identity(ID, IDENTITY_REF, NAME, SURNAME, BIRTH_DATE, VILLAGE_OF_ORIGIN, PLACE_OF_BIRTH, DATE_OF_ISSUE);

        IdentityJson identityJson = toIdentityJson(identity);

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
    @DisplayName("Should return empty IdentityJson when IdentityDTO is null")
    void returnEmptyIdentityJsonToDTO() {
        IdentityJson identityJson = toIdentityJson(null);

        assertThat(identityJson).isNotNull();
    }
}
