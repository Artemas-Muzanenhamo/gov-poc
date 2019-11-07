package com.gov.zw.service;

import com.gov.zw.client.Identity;
import com.gov.zw.client.IdentityClient;
import com.gov.zw.client.IdentityReferenceJson;
import com.gov.zw.client.dto.IdentityReference;
import com.gov.zw.dto.License;
import com.gov.zw.exception.InvalidLicenseException;
import com.gov.zw.repository.LicenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
class LicenseServiceTest {

    private static final String ID_REF = "1";
    private static final String ID = "1";
    private static final String IDENTITY_REF = "1";
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

    @InjectMocks
    private LicenseServiceImpl licenseService;
    @Mock
    private IdentityClient identityClient;
    @Mock
    private LicenseRepository licenseRepository;

    @BeforeEach
    void setUp() {
        initMocks(this);
        licenseService = new LicenseServiceImpl(identityClient, licenseRepository);
    }

    @Test
    void should_return_an_identity() throws Exception {

        License license = new License(ID, IDENTITY_REF, SURNAME, FIRST_NAMES,
                DATE_OF_BIRTH, COUNTRY, DATE_OF_ISSUE,
                EXPIRY_DATE, AGENCY, LICENSE_NUMBER, SIGNATURE_IMAGE,
                ADDRESS);
        Identity identity = new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990", "Mashayamombe",
                "Harare", "22/01/2018");
        IdentityReferenceJson identityReferenceJson = new IdentityReferenceJson(ID_REF);
        given(identityClient.findIdentityByIdReferenceNumber(identityReferenceJson)).willReturn(identity);

        licenseService.addLicense(license);

        verify(identityClient, times(1)).findIdentityByIdReferenceNumber(identityReferenceJson);
    }

    @Test
    @DisplayName("Should throw an InvalidIdentityException when an ID ref that is not an INT is passed")
    void shouldThrowAnInvalidIdentityExceptionFromInvalidStringIdRef() {
        License license = new License();
        license.setId("Artemas");

        assertThrows(InvalidLicenseException.class, () -> licenseService.addLicense(license));
    }

    @Test
    void should_return_an_identity_not_valid_exception() {
        License license = new License();
        assertThrows(InvalidLicenseException.class, () -> licenseService.addLicense(license));
    }

    @Test
    void should_return_licenses_from_the_repository() {
        License license = new License(ID, IDENTITY_REF, SURNAME, FIRST_NAMES,
                DATE_OF_BIRTH, COUNTRY, DATE_OF_ISSUE,
                EXPIRY_DATE, AGENCY, LICENSE_NUMBER, SIGNATURE_IMAGE,
                ADDRESS);
        List<License> licenses = Collections.singletonList(license);
        given(licenseRepository.findAll()).willReturn(licenses);

        List<License> allLicenses = licenseService.getAllLicenses();

        assertThat(allLicenses).isNotEmpty();
        License expectedLicense = allLicenses.get(0);
        assertThat(expectedLicense.getId()).isEqualTo(ID);
        assertThat(expectedLicense.getIdentityRef()).isEqualTo(IDENTITY_REF);
        assertThat(expectedLicense.getSurname()).isEqualTo(SURNAME);
        assertThat(expectedLicense.getFirstNames()).isEqualTo(FIRST_NAMES);
        assertThat(expectedLicense.getDateOfBirth()).isEqualTo(DATE_OF_BIRTH);
        assertThat(expectedLicense.getCountry()).isEqualTo(COUNTRY);
        assertThat(expectedLicense.getDateOfIssue()).isEqualTo(DATE_OF_ISSUE);
        assertThat(expectedLicense.getExpiryDate()).isEqualTo(EXPIRY_DATE);
        assertThat(expectedLicense.getAgency()).isEqualTo(AGENCY);
        assertThat(expectedLicense.getLicenseNumber()).isEqualTo(LICENSE_NUMBER);
        assertThat(expectedLicense.getSignatureImage()).isEqualTo(SIGNATURE_IMAGE);
        assertThat(expectedLicense.getAddress()).isEqualTo(ADDRESS);
        verify(licenseRepository, times(1)).findAll();
    }

    @Test
    void should_save_when_empty_license_details_are_passed() throws Exception {
        License license = new License();

        licenseService.updateLicense(license);

        verify(this.licenseRepository, times(1)).save(license);
    }

    @Test
    void should_update_license_details_when_a_valid_license_is_passed() throws Exception {
        License license = new License(ID, IDENTITY_REF, SURNAME, FIRST_NAMES,
                DATE_OF_BIRTH, COUNTRY, DATE_OF_ISSUE,
                EXPIRY_DATE, AGENCY, LICENSE_NUMBER, SIGNATURE_IMAGE,
                ADDRESS);

        licenseService.updateLicense(license);

        verify(licenseRepository, times(1)).save(license);
    }

    @Test
    void should_delete_a_license_when_a_valid_license_is_passed() throws Exception {
        License license = new License(ID, IDENTITY_REF, SURNAME, FIRST_NAMES,
                DATE_OF_BIRTH, COUNTRY, DATE_OF_ISSUE,
                EXPIRY_DATE, AGENCY, LICENSE_NUMBER, SIGNATURE_IMAGE,
                ADDRESS);

        licenseService.removeLicense(license);

        verify(licenseRepository, times(1)).delete(license);
    }

    @Test
    void should_return_a_license_given_the_identity_reference() throws Exception {
        License license = new License(ID, IDENTITY_REF, SURNAME, FIRST_NAMES,
                DATE_OF_BIRTH, COUNTRY, DATE_OF_ISSUE,
                EXPIRY_DATE, AGENCY, LICENSE_NUMBER, SIGNATURE_IMAGE,
                ADDRESS);
        IdentityReference identityReference = new IdentityReference(ID_REF);
        given(licenseRepository.findLicenseByIdentityRef(ID_REF)).willReturn(license);

        License licenseByIdentityRef = licenseService.getLicenseByIdentityRef(identityReference);

        assertThat(licenseByIdentityRef).isEqualTo(license);
        verify(licenseRepository, times(1)).findLicenseByIdentityRef(ID_REF);
    }
}
