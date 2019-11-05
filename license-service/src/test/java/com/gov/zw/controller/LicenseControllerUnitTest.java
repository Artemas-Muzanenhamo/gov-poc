package com.gov.zw.controller;

import com.gov.zw.client.IdentityReferenceJson;
import com.gov.zw.client.dto.IdentityReference;
import com.gov.zw.domain.LicenseJson;
import com.gov.zw.dto.License;
import com.gov.zw.exception.InvalidLicenseException;
import com.gov.zw.service.LicenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LicenseControllerUnitTest {

    private static final String ID = "MUZAN1234";
    private static final String IDENTITY_REF = "121";
    private static final String SURNAME = "Muzanenhamo";
    private static final String FIRST_NAMES = "Artemas";
    private static final String DATE_OF_BIRTH = "28/03/1990";
    private static final String COUNTRY = "United Kingdom";
    private static final String DATE_OF_ISSUE = "28/03/2010";
    private static final String EXPIRY_DATE = "28/03/2060";
    private static final String AGENCY = "DVLA";
    private static final String LICENSE_NUMBER = "MUZANK9843ACTK";
    private static final String SIGNATURE_IMAGE = "001.jpg";
    private static final String ADDRESS = "27 Foxhill Street, Guildford, Surrey, GU21 9EE";
    public static final String ID_REF = "some reference";
    private LicenseController licenseController;
    @Mock
    private LicenseService licenseServiceImpl;

    @BeforeEach
    void setUp() {
        licenseController = new LicenseController(licenseServiceImpl);
    }

    @Test
    @DisplayName("Should add license given a valid license")
    void addLicense() throws Exception {
        License license = new License(ID, IDENTITY_REF, SURNAME, FIRST_NAMES,
                DATE_OF_BIRTH, COUNTRY, DATE_OF_ISSUE,
                EXPIRY_DATE, AGENCY, LICENSE_NUMBER, SIGNATURE_IMAGE,
                ADDRESS);
        LicenseJson licenseJson = new LicenseJson(license);

        licenseController.addLicense(licenseJson);

        verify(licenseServiceImpl).addLicense(license);
    }

    @Test
    @DisplayName("Should get all licenses")
    void getAllLicenses() throws Exception {
        List<License> licenseList = singletonList(new License(ID, IDENTITY_REF, SURNAME, FIRST_NAMES,
                DATE_OF_BIRTH, COUNTRY, DATE_OF_ISSUE,
                EXPIRY_DATE, AGENCY, LICENSE_NUMBER, SIGNATURE_IMAGE,
                ADDRESS));
        given(licenseServiceImpl.getAllLicenses()).willReturn(licenseList);

        List<LicenseJson> licenses = licenseController.getAllLicenses();

        assertThat(licenses).isNotEmpty();
        LicenseJson licenseJson = licenses.get(0);
        assertThat(licenseJson).isNotNull();
        assertThat(licenseJson.getId()).isEqualTo(ID);
        assertThat(licenseJson.getIdentityRef()).isEqualTo(IDENTITY_REF);
        assertThat(licenseJson.getSurname()).isEqualTo(SURNAME);
        assertThat(licenseJson.getFirstNames()).isEqualTo(FIRST_NAMES);
        assertThat(licenseJson.getDateOfBirth()).isEqualTo(DATE_OF_BIRTH);
        assertThat(licenseJson.getCountry()).isEqualTo(COUNTRY);
        assertThat(licenseJson.getDateOfIssue()).isEqualTo(DATE_OF_ISSUE);
        assertThat(licenseJson.getExpiryDate()).isEqualTo(EXPIRY_DATE);
        assertThat(licenseJson.getAgency()).isEqualTo(AGENCY);
        assertThat(licenseJson.getLicenseNumber()).isEqualTo(LICENSE_NUMBER);
        assertThat(licenseJson.getSignatureImage()).isEqualTo(SIGNATURE_IMAGE);
        assertThat(licenseJson.getAddress()).isEqualTo(ADDRESS);
    }

    @Test
    @DisplayName("Should return a License given a valid identity reference")
    void getLicenseByIdentityRef() throws Exception {
        IdentityReference identityReference = new IdentityReference(ID_REF);
        IdentityReferenceJson identityRefJson = new IdentityReferenceJson();
        License license = new License(ID, IDENTITY_REF, SURNAME, FIRST_NAMES,
                DATE_OF_BIRTH, COUNTRY, DATE_OF_ISSUE,
                EXPIRY_DATE, AGENCY, LICENSE_NUMBER, SIGNATURE_IMAGE,
                ADDRESS);
        given(licenseServiceImpl.getLicenseByIdentityRef(identityReference)).willReturn(license);

        LicenseJson licenseJson = licenseController.getLicenseByIdentityRef(identityRefJson);

        assertThat(licenseJson).isNotNull();
    }
}
