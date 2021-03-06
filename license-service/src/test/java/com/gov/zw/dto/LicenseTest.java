package com.gov.zw.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

class LicenseTest {

    private License license;
    private License license2;

    @BeforeEach
    void init() {
        license = new License(
                "1", "1234AMUZ1", "Muzanenhamo", "Artemas Takudzwa",
                "28/03/1990", "ZIM", "20/11/2017", "19/11/2027", "ZDVLA",
                "MUZANEN123456ABCDEF", "signature.jpg", "768 Sunningdale 3, Harare, Zimbabwe");

        license2 = new License(
                "1", "1234AMUZ1", "Muzanenhamo", "Artemas Takudzwa",
                "28/03/1990", "ZIM", "20/11/2017", "19/11/2027", "ZDVLA",
                "MUZANEN123456ABCDEF", "signature.jpg", "768 Sunningdale 3, Harare, Zimbabwe");
    }

    @Test
    void creation() {

        Assertions.assertThat(license.getId()).isEqualTo("1");
        Assertions.assertThat(license.getIdentityRef()).isEqualTo("1234AMUZ1");
        Assertions.assertThat(license.getSurname()).isEqualTo("Muzanenhamo");
        Assertions.assertThat(license.getFirstNames()).isEqualTo("Artemas Takudzwa");
        Assertions.assertThat(license.getDateOfBirth()).isEqualTo("28/03/1990");
        Assertions.assertThat(license.getCountry()).isEqualTo("ZIM");
        Assertions.assertThat(license.getDateOfIssue()).isEqualTo("20/11/2017");
        Assertions.assertThat(license.getExpiryDate()).isEqualTo("19/11/2027");
        Assertions.assertThat(license.getAgency()).isEqualTo("ZDVLA");
        Assertions.assertThat(license.getLicenseNumber()).isEqualTo("MUZANEN123456ABCDEF");
        Assertions.assertThat(license.getSignatureImage()).isEqualTo("signature.jpg");
        Assertions.assertThat(license.getAddress()).isEqualTo("768 Sunningdale 3, Harare, Zimbabwe");

        Assertions.assertThat(license).isEqualTo(license2);
    }


    @Test
    void licenceIsEqual() {
        assertEquals(true, license.equals(license2));
    }

    @Test
    void licenseNotEqual() {
        License license3 = new License();
        assertNotEquals(true, license.equals(license3));
    }

    @Test
    void hashCodesNotEqual(){
        assertNotEquals(false,license.hashCode() == license2.hashCode());
    }

    @Test
    void hashCodesEqual(){
        license2 = license;
        assertEquals(true, license.hashCode() == license2.hashCode());
    }

    @Test
    void emptyToString(){
        License license = new License();
        License license1 = new License();
        assertEquals(license1.toString(), license.toString());
    }

}
