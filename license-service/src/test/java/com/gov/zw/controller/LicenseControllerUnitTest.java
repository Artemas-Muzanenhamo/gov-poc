package com.gov.zw.controller;

import com.gov.zw.domain.LicenseJson;
import com.gov.zw.dto.License;
import com.gov.zw.service.LicenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        // TODO
//        License license = new License(ID, IDENTITY_REF, SURNAME, FIRST_NAMES,
//                DATE_OF_BIRTH, COUNTRY, DATE_OF_ISSUE,
//                EXPIRY_DATE, AGENCY, LICENSE_NUMBER, SIGNATURE_IMAGE,
//                ADDRESS);
//        LicenseJson licenseJson = new LicenseJson(license);
//
//        List<LicenseJson> licenses = licenseController.getAllLicenses();
//
//        assertThat();
    }
}
