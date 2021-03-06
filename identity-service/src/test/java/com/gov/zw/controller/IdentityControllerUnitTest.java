package com.gov.zw.controller;

import com.gov.zw.domain.IdentityJson;
import com.gov.zw.domain.IdentityNameJson;
import com.gov.zw.domain.IdentityReferenceJson;
import com.gov.zw.dto.Identity;
import com.gov.zw.dto.IdentityName;
import com.gov.zw.dto.IdentityReference;
import com.gov.zw.exception.InvalidIdentityException;
import com.gov.zw.service.IdentityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

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

        verify(identityServiceImpl).save(identity);
    }

    @Test
    @DisplayName("Should throw an Invalid Identity Exception when Identity passed in is not valid")
    void saveIdentityException() throws Exception{
        Identity identity = new Identity();
        IdentityJson identityJson = new IdentityJson();
        doThrow(InvalidIdentityException.class).when(identityServiceImpl).save(identity);

        assertThrows(InvalidIdentityException.class, () -> identityController.saveIdentity(identityJson));
    }

    @Test
    @DisplayName("Should update an identity given a valid identity")
    void updateIdentity() throws Exception {
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

        identityController.updateIdentity(identityJson);

        verify(identityServiceImpl).save(identity);
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
        IdentityJson identityJson = identitiesByName.get(0);
        assertThat(identityJson.getId()).isEqualTo(ID);
        assertThat(identityJson.getIdentityRef()).isEqualTo(IDENTITY_REF);
        assertThat(identityJson.getName()).isEqualTo(NAME);
        assertThat(identityJson.getSurname()).isEqualTo(SURNAME);
        assertThat(identityJson.getBirthDate()).isEqualTo(BIRTH_DATE);
        assertThat(identityJson.getVillageOfOrigin()).isEqualTo(VILLAGE_OF_ORIGIN);
        assertThat(identityJson.getPlaceOfBirth()).isEqualTo(PLACE_OF_BIRTH);
        assertThat(identityJson.getDateOfIssue()).isEqualTo(DATE_OF_ISSUE);
        verify(identityServiceImpl).findIdentitiesByName(identityName);
    }

    @Test
    @DisplayName("Should return identities by identity reference")
    void getIdentitiesByIdentityReference() throws Exception {
        IdentityReferenceJson identityReferenceJson = new IdentityReferenceJson(IDENTITY_REF);
        IdentityReference identityReference = new IdentityReference(IDENTITY_REF);
        Identity identity = new Identity(ID, IDENTITY_REF, NAME, SURNAME,
                BIRTH_DATE, VILLAGE_OF_ORIGIN,
                PLACE_OF_BIRTH, DATE_OF_ISSUE);
        given(identityServiceImpl.findIdentityByIdentityRef(identityReference)).willReturn(identity);

        IdentityJson identityJson = identityController.getIdentityByReferenceNumber(identityReferenceJson);

        assertThat(identityJson).isNotNull();
        assertThat(identityJson.getId()).isEqualTo(ID);
        assertThat(identityJson.getIdentityRef()).isEqualTo(IDENTITY_REF);
        assertThat(identityJson.getName()).isEqualTo(NAME);
        assertThat(identityJson.getSurname()).isEqualTo(SURNAME);
        assertThat(identityJson.getBirthDate()).isEqualTo(BIRTH_DATE);
        assertThat(identityJson.getVillageOfOrigin()).isEqualTo(VILLAGE_OF_ORIGIN);
        assertThat(identityJson.getPlaceOfBirth()).isEqualTo(PLACE_OF_BIRTH);
        assertThat(identityJson.getDateOfIssue()).isEqualTo(DATE_OF_ISSUE);
        verify(identityServiceImpl).findIdentityByIdentityRef(identityReference);
    }

    @Test
    @DisplayName("Should return all identities")
    void getAllIdentities() {
        Identity identity = new Identity(ID, IDENTITY_REF, NAME, SURNAME,
                BIRTH_DATE, VILLAGE_OF_ORIGIN,
                PLACE_OF_BIRTH, DATE_OF_ISSUE);
        List<Identity> identityList = new ArrayList<>();
        identityList.add(identity);
        given(identityServiceImpl.findAll()).willReturn(identityList);

        List<IdentityJson> identities = identityController.getIdentities();

        assertThat(identities).isNotEmpty();
        IdentityJson identityJson = identities.get(0);
        assertThat(identityJson).isNotNull();
        assertThat(identityJson.getId()).isEqualTo(ID);
        assertThat(identityJson.getIdentityRef()).isEqualTo(IDENTITY_REF);
        assertThat(identityJson.getName()).isEqualTo(NAME);
        assertThat(identityJson.getSurname()).isEqualTo(SURNAME);
        assertThat(identityJson.getBirthDate()).isEqualTo(BIRTH_DATE);
        assertThat(identityJson.getVillageOfOrigin()).isEqualTo(VILLAGE_OF_ORIGIN);
        assertThat(identityJson.getPlaceOfBirth()).isEqualTo(PLACE_OF_BIRTH);
        assertThat(identityJson.getDateOfIssue()).isEqualTo(DATE_OF_ISSUE);
        verify(identityServiceImpl).findAll();
    }

    @Test
    @DisplayName("Should delete an identity given a valid identity")
    void deleteIdentity() throws Exception {
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

        identityController.deleteIdentity(identityJson);

        verify(identityServiceImpl).delete(identity);
    }
}