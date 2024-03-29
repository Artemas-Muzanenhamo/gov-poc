package com.gov.zw.service;

import com.gov.zw.domain.Identity;
import com.gov.zw.domain.IdentityName;
import com.gov.zw.domain.IdentityReference;
import com.gov.zw.repository.IdentityRepository;
import com.gov.zw.exception.InvalidIdentityException;
import com.gov.zw.exception.InvalidIdentityNameException;
import com.gov.zw.exception.InvalidIdentityReferenceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class IdentityServiceTest {

    private static final String ID = "1";
    private static final String IDENTITY_REF = "1";
    private static final String NAME = "Artemas";
    private static final String SURNAME = "Muzanenhamo";
    private static final String BIRTH_DATE = "28/03/1990";
    private static final String VILLAGE_OF_ORIGIN = "Mashayamombe";
    private static final String PLACE_OF_BIRTH = "Harare";
    private static final String DATE_OF_ISSUE = "17/11/2017";
    @InjectMocks
    private IdentityServiceImpl identityService;
    @Mock
    private IdentityRepository identityRepository;

    @Test
    @DisplayName("Should throw an InvalidIdentityException when an invalid identity is passed")
    void throwExceptionWhenInvalidIdentityIsPassed() {
        assertThrows(InvalidIdentityException.class, () -> identityService.save(null));
    }

    @Test
    @DisplayName("Should save identity if identity details exist")
    void saveIdentity() throws InvalidIdentityException {
        Identity identity = new Identity(ID, IDENTITY_REF, NAME, SURNAME, BIRTH_DATE,
                VILLAGE_OF_ORIGIN, PLACE_OF_BIRTH, DATE_OF_ISSUE);

        identityService.save(identity);

        verify(identityRepository, times(1)).save(identity);
    }

    @Test
    @DisplayName("Should find identities by name")
    void findIdentitiesByName() throws InvalidIdentityNameException {
        List<Identity> identities = Collections.singletonList(new Identity(ID, IDENTITY_REF, NAME, SURNAME, BIRTH_DATE,
                VILLAGE_OF_ORIGIN, PLACE_OF_BIRTH, DATE_OF_ISSUE));
        IdentityName identityName = new IdentityName("Artemas");
        given(identityRepository.findIdentitiesByName(identityName.getName())).willReturn(identities);

        List<Identity> identitiesByName = identityService.findIdentitiesByName(identityName);

        assertThat(identitiesByName).isNotEmpty();
    }

    @Test
    @DisplayName("Should throw an InvalidIdentityNameException when an invalid name is passed")
    void throwExceptionWhenInvalidNameIsPassed() {
        assertThrows(InvalidIdentityNameException.class, () -> identityService.findIdentitiesByName(null));
    }

    @Test
    @DisplayName("Should throw an InvalidIdentityReferenceException when an invalid IdRef is passed")
    void throwExceptionWhenInvalidIdRefIsPassed() {
        assertThrows(InvalidIdentityReferenceException.class, () -> identityService.findIdentityByIdentityRef(null));
    }

    @Test
    @DisplayName("Should return an identity by identity reference")
    void getIdentityByIdReference() throws InvalidIdentityReferenceException {
        Identity identity = new Identity(ID, IDENTITY_REF, NAME, SURNAME, BIRTH_DATE,
                VILLAGE_OF_ORIGIN, PLACE_OF_BIRTH, DATE_OF_ISSUE);
        IdentityReference identityReference = new IdentityReference(IDENTITY_REF);
        given(identityRepository.findIdentityByIdentityRef(identity.getIdentityRef())).willReturn(identity);

        Identity identityByIdentityRef = identityService.findIdentityByIdentityRef(identityReference);

        assertThat(identityByIdentityRef).isEqualTo(identity);
        assertThat(identityByIdentityRef.getName()).isEqualTo("Artemas");
    }

    @Test
    @DisplayName("Should return a list of all identities")
    void returnAllIdentities() {
        List<Identity> identities = Arrays.asList(
                new Identity(ID, IDENTITY_REF, NAME, SURNAME, BIRTH_DATE,
                        VILLAGE_OF_ORIGIN, PLACE_OF_BIRTH, DATE_OF_ISSUE),
                new Identity(ID, IDENTITY_REF, NAME, SURNAME, BIRTH_DATE,
                        VILLAGE_OF_ORIGIN, PLACE_OF_BIRTH, DATE_OF_ISSUE),
                new Identity(ID, IDENTITY_REF, NAME, SURNAME, BIRTH_DATE,
                        VILLAGE_OF_ORIGIN, PLACE_OF_BIRTH, DATE_OF_ISSUE)
        );
        given(identityRepository.findAll()).willReturn(identities);

        List<Identity> identityJsonList = identityService.findAll();

        assertThat(identityJsonList).isNotEmpty().hasSize(3);
        Identity identity = identityJsonList.get(0);
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
    @DisplayName("Should throw an InvalidIdentityException when an invalid identity is passed to be deleted")
    void throwExceptionWhenInvalidIdentityIsToBeDeleted() {
        assertThrows(InvalidIdentityException.class, () -> identityService.delete(null));
    }

    @Test
    @DisplayName("Should delete an Identity")
    void deleteIdentity() throws InvalidIdentityException {
        Identity identity = new Identity(ID, IDENTITY_REF, NAME, SURNAME, BIRTH_DATE,
                VILLAGE_OF_ORIGIN, PLACE_OF_BIRTH, DATE_OF_ISSUE);

        identityService.delete(identity);

        verify(identityRepository, times(1)).delete(identity);
    }
}
