package com.gov.zw.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LicenseJsonMapperTest {

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
    private LicenseJsonMapper licenseJsonMapper;

    @BeforeEach
    void init() {
        licenseJsonMapper = new LicenseJsonMapper();
    }

    @Test
    void should_map_license_json_to_a_license_object() {
        License expectedLicense = new License(ID, IDENTITY_REF, SURNAME, FIRST_NAMES,
                DATE_OF_BIRTH, COUNTRY, DATE_OF_ISSUE,
                EXPIRY_DATE, AGENCY, LICENSE_NUMBER, SIGNATURE_IMAGE,
                ADDRESS);
        LicenseJson licenseJson = new LicenseJson(expectedLicense);

        License license = licenseJsonMapper.toDto(licenseJson);

        assertThat(license).isEqualTo(expectedLicense);
    }
}
