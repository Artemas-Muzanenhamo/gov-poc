package com.gov.zw.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by Artemas on 23/11/2017.
 */
public class LicenseTest {

    @Test
    public void creation(){
        License license = new License(
                "Muzanenhamo", "Artemas Takudzwa", "28/03/1990", "ZIM", "20/11/2017", "19/11/2027", "ZDVLA",
                "MUZANEN123456ABCDEF", "signature.jpg", "768 Sunningdale 3, Harare, Zimbabwe");

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
    }

}
