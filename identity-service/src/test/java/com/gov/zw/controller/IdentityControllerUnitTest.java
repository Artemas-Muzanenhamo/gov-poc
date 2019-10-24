package com.gov.zw.controller;

import com.gov.zw.domain.Identity;
import com.gov.zw.domain.IdentityJson;
import com.gov.zw.domain.IdentityNameJson;
import com.gov.zw.dto.IdentityName;
import com.gov.zw.service.IdentityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class IdentityControllerUnitTest {

    private static final String ID = "1";
    private static final String IDENTITY_REF = "MUZAN1234";
    private static final String NAME = "Artemas";
    private static final String SURNAME = "Muzanenhamo";
    private static final String BIRTH_DATE = "28/03/1990";
    private static final String VILLAGE_OF_ORIGIN = "Mashayamombe";
    private static final String PLACE_OF_BIRTH = "Harare";
    private static final String DATE_OF_ISSUE = "22/01/2018";
    private IdentityController identityController;

    @Mock
    private IdentityService identityServiceImpl;

    @BeforeEach
    void setup() {
        identityController = new IdentityController(identityServiceImpl);
    }

    @Test
    @DisplayName("Should save an identity given a valid identity")
    void saveIdentity() throws Exception {
        Identity identity = new Identity(ID, IDENTITY_REF, NAME, SURNAME,
                BIRTH_DATE, VILLAGE_OF_ORIGIN,
                PLACE_OF_BIRTH, DATE_OF_ISSUE);
        IdentityJson identityJson = new IdentityJson(
                identity.getId(),
                identity.getIdentityRef(),
                identity.getName(),
                identity.getSurname(),
                identity.getBirthDate(),
                identity.getVillageOfOrigin(),
                identity.getPlaceOfBirth(),
                identity.getDateOfIssue());

        identityController.saveIdentity(identityJson);

        Mockito.verify(identityServiceImpl).save(identity);

    }

    @Test
    @DisplayName("Should return identities by name")
    void getIdentitiesByName() throws Exception {
        IdentityNameJson identityNameJson = new IdentityNameJson(NAME);
        IdentityName identityName = new IdentityName(NAME);
        Identity identity = new Identity(ID, IDENTITY_REF, NAME, SURNAME,
                BIRTH_DATE, VILLAGE_OF_ORIGIN,
                PLACE_OF_BIRTH, DATE_OF_ISSUE);
        List<Identity> identities = new ArrayList<>();
        identities.add(identity);
        given(identityServiceImpl.findIdentitiesByName(identityName)).willReturn(identities);

        List<IdentityJson> identitiesByName = identityController.getIdentitiesByName(identityNameJson);

        assertThat(identitiesByName).isNotEmpty();
        assertThat(identitiesByName.get(0)).isNotNull();
        //TODO: Assert field values here...
    }
}