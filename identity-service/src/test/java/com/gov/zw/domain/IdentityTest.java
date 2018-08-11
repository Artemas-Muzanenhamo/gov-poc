package com.gov.zw.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class IdentityTest {

    @Test
    public void creation(){
        Identity identity = new Identity(
                "1", "1234AM1", "Artemas", "Muzanenhamo", "28/03/1990", "Mashayamombe",
                "Harare", "22/11/2017");

        Assertions.assertThat(identity.getIdentityRef()).isEqualTo("1234AM1");
        Assertions.assertThat(identity.getName()).isEqualTo("Artemas");
        Assertions.assertThat(identity.getSurname()).isEqualTo("Muzanenhamo");
        Assertions.assertThat(identity.getBirthDate()).isEqualTo("28/03/1990");
        Assertions.assertThat(identity.getVillageOfOrigin()).isEqualTo("Mashayamombe");
        Assertions.assertThat(identity.getPlaceOfBirth()).isEqualTo("Harare");
        Assertions.assertThat(identity.getDateOfIssue()).isEqualTo("22/11/2017");
    }
}
