package com.gov.zw.mapper;

import com.gov.zw.dto.License;
import com.gov.zw.domain.LicenseJson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static com.gov.zw.mapper.LicenseMapper.*;
import static org.assertj.core.api.Assertions.assertThat;

class LicenseMapperTest {

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

    @Test
    @DisplayName("Should map LicenseJson to a LicenseDTO")
    void mapLicenseJsonToDTO() {
        License expectedLicense = new License(ID, IDENTITY_REF, SURNAME, FIRST_NAMES,
                DATE_OF_BIRTH, COUNTRY, DATE_OF_ISSUE,
                EXPIRY_DATE, AGENCY, LICENSE_NUMBER, SIGNATURE_IMAGE,
                ADDRESS);
        LicenseJson licenseJson = new LicenseJson(expectedLicense);

        License license = toLicenseDTO(licenseJson);

        assertThat(license).isEqualTo(expectedLicense);
    }

    @Test
    @DisplayName("Should return empty LicenseDTO when LicenseJson is null")
    void returnEmptyLicenseDTOFromNullJson() {
        License license = toLicenseDTO(null);

        assertThat(license).isNotNull();
    }

    @Test
    @DisplayName("Should map LicenseDTO to LicenseJson")
    void mapLicenseDTOToJson() {
        License license = new License(ID, IDENTITY_REF, SURNAME, FIRST_NAMES,
                DATE_OF_BIRTH, COUNTRY, DATE_OF_ISSUE,
                EXPIRY_DATE, AGENCY, LICENSE_NUMBER, SIGNATURE_IMAGE,
                ADDRESS);
        LicenseJson licenseJson = toLicenseJson(license);

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
    @DisplayName("Should return an empty LicenseJson when LicenseDTO is null")
    void nullLicenseDTO() {
        LicenseJson licenseJson = toLicenseJson(null);

        assertThat(licenseJson).isNotNull();
    }

    @Test
    @DisplayName("Should map LicenseDTO list to LicenseJson list")
    void mapLicenseDTOListToJsonList() {
        License license = new License(ID, IDENTITY_REF, SURNAME, FIRST_NAMES,
                DATE_OF_BIRTH, COUNTRY, DATE_OF_ISSUE,
                EXPIRY_DATE, AGENCY, LICENSE_NUMBER, SIGNATURE_IMAGE,
                ADDRESS);
        List<License> licenses = Collections.singletonList(license);

        List<LicenseJson> licenseJsonList = toLicenseJsonList(licenses);

        assertThat(licenseJsonList).isNotEmpty();
        LicenseJson licenseJson = licenseJsonList.get(0);
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
    @DisplayName("Should return an emptyList of LicenseJson when the list of LicenseDTO is empty or null")
    void nullListOfLicenseDTOs() {
        List<LicenseJson> licenseJsonList = toLicenseJsonList(null);

        assertThat(licenseJsonList).isEmpty();
    }
}
