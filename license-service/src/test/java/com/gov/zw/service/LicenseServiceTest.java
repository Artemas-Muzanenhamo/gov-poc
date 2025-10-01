package com.gov.zw.service;

import com.gov.zw.client.Identity;
import com.gov.zw.client.IdentityClient;
import com.gov.zw.client.dto.IdentityReference;
import com.gov.zw.domain.License;
import com.gov.zw.exception.InvalidIdentityException;
import com.gov.zw.exception.InvalidLicenseException;
import com.gov.zw.repository.LicenseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class LicenseServiceTest {

    private static final String ID_REF = "1";
    private static final String ID = "1";
    private static final String IDENTITY_REF = "ABC123";
    private static final String SURNAME = "Muzanenhamo";
    private static final String FIRST_NAMES = "Artemas";
    private static final String DATE_OF_BIRTH = "28/03/1990";
    private static final String COUNTRY = "Zimbabwe";
    private static final String DATE_OF_ISSUE = "25 January 2018";
    private static final String EXPIRY_DATE = "25 January 2050";
    private static final String AGENCY = "DVLA";
    private static final String LICENSE_NUMBER = "MUZANATCK1990";
    private static final String SIGNATURE_IMAGE = "Doc1.png";
    private static final String ADDRESS = "150 Sunningdale road";
    private static final String NAME = "Artemas";
    private static final String BIRTH_DATE = "28/03/1990";
    private static final String VILLAGE_OF_ORIGIN = "Mashayamombe";
    private static final String PLACE_OF_BIRTH = "Harare";

    @InjectMocks
    private LicenseServiceImpl licenseService;
    @Mock
    private IdentityClient identityClient;
    @Mock
    private LicenseRepository licenseRepository;
    private static final String LICENSE_EXCEPTION_MESSAGE = "The license is invalid!";
    private static final String IDENTITY_EXCEPTION_MESSAGE = "Identity is invalid or does not exist!";

    @Test
    @DisplayName("Should return an identity")
    void returnIdentityByReference() throws Exception {

        License license = new License(ID, IDENTITY_REF, SURNAME, FIRST_NAMES,
                DATE_OF_BIRTH, COUNTRY, DATE_OF_ISSUE,
                EXPIRY_DATE, AGENCY, LICENSE_NUMBER, SIGNATURE_IMAGE,
                ADDRESS);
        Identity identity = new Identity(ID, IDENTITY_REF, NAME, SURNAME, BIRTH_DATE, VILLAGE_OF_ORIGIN,
                PLACE_OF_BIRTH, DATE_OF_ISSUE);
        IdentityReference identityReference = new IdentityReference(IDENTITY_REF);
        given(identityClient.findIdentityByIdReferenceNumber(identityReference)).willReturn(identity);

        licenseService.addLicense(license);

        then(identityClient).should().findIdentityByIdReferenceNumber(identityReference);
    }

    @Test
    @DisplayName("Should throw an InvalidIdentityException when an ID ref that is not an INT is passed")
    void throwExceptionWhenIdRefIsNotAnInt() {
        License license = new License(ID, IDENTITY_REF, null, null, null, null, null, null, null, null, null, null);

        InvalidIdentityException exception = assertThrows(InvalidIdentityException.class, () -> licenseService.addLicense(license));

        assertThat(exception.getMessage()).isEqualTo(IDENTITY_EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("Should throw an InvalidLicenseException when license is empty")
    void throwExceptionWhenLicenseIsEmpty() {
        License license = License.empty();

        InvalidLicenseException exception = assertThrows(InvalidLicenseException.class, () -> licenseService.addLicense(license));

        assertThat(exception.getMessage()).isEqualTo(LICENSE_EXCEPTION_MESSAGE);
        
        then(identityClient).shouldHaveNoInteractions();
        then(licenseRepository).shouldHaveNoInteractions();
    }

    @Test
    @DisplayName("Should return all licenses")
    void returnAllLicenses() {
        License license = new License(ID, IDENTITY_REF, SURNAME, FIRST_NAMES,
                DATE_OF_BIRTH, COUNTRY, DATE_OF_ISSUE,
                EXPIRY_DATE, AGENCY, LICENSE_NUMBER, SIGNATURE_IMAGE,
                ADDRESS);
        List<License> licenses = Collections.singletonList(license);
        given(licenseRepository.findAll()).willReturn(licenses);

        List<License> allLicenses = licenseService.getAllLicenses();

        assertThat(allLicenses).isNotEmpty();
        assertThat(allLicenses).containsExactly(license);
        
        then(licenseRepository).should().findAll();
    }

    @Test
    @DisplayName("Should throw InvalidLicenseException when an empty license is passed")
    void throwExceptionWhenLicenseIsEmptyWhileUpdating() {
        License license = License.empty();

        assertThrows(InvalidLicenseException.class, () -> licenseService.updateLicense(license));

        then(identityClient).shouldHaveNoInteractions();
        then(this.licenseRepository).shouldHaveNoInteractions();
    }

    @Test
    @DisplayName("Should update license details when a valid license is passed")
    void updateLicenseDetails() throws Exception {
        License license = new License(ID, IDENTITY_REF, SURNAME, FIRST_NAMES,
                DATE_OF_BIRTH, COUNTRY, DATE_OF_ISSUE,
                EXPIRY_DATE, AGENCY, LICENSE_NUMBER, SIGNATURE_IMAGE,
                ADDRESS);

        licenseService.updateLicense(license);

        then(licenseRepository).should().save(license);
    }

    @Test
    @DisplayName("Should delete a license")
    void shouldDeleteALicense() throws Exception {
        License license = new License(ID, IDENTITY_REF, SURNAME, FIRST_NAMES,
                DATE_OF_BIRTH, COUNTRY, DATE_OF_ISSUE,
                EXPIRY_DATE, AGENCY, LICENSE_NUMBER, SIGNATURE_IMAGE,
                ADDRESS);

        licenseService.removeLicense(license);

        then(licenseRepository).should().delete(license);
    }

    @Test
    @DisplayName("Should return a license given the identity reference")
    void returnLicenseByIdentityReference() throws Exception {
        License license = new License(ID, IDENTITY_REF, SURNAME, FIRST_NAMES,
                DATE_OF_BIRTH, COUNTRY, DATE_OF_ISSUE,
                EXPIRY_DATE, AGENCY, LICENSE_NUMBER, SIGNATURE_IMAGE,
                ADDRESS);
        IdentityReference identityReference = new IdentityReference(ID_REF);
        given(licenseRepository.findLicenseByIdentityRef(ID_REF)).willReturn(license);

        License licenseByIdentityRef = licenseService.getLicenseByIdentityRef(identityReference);

        assertThat(licenseByIdentityRef).isEqualTo(license);
        
        then(licenseRepository).should().findLicenseByIdentityRef(ID_REF);
    }
}
