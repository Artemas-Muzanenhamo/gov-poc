package com.gov.zw.domain;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LicenseJsonTest {
    private LicenseJson licenseJson;

    @BeforeEach
    public void init() {
        licenseJson = new LicenseJson();
    }

    @Test
    public void equality_check() {
        LicenseJson expectedLicenseJson = new LicenseJson(new License("MUZAN1234", "121", "Muzanenhamo", "Artemas",
                "28/03/1990", "United Kingdom", "28/03/2010",
                "28/03/2060", "DVLA", "MUZANK9843ACTK", "001.jpg",
                "27 Foxhill Street, Guildford, Surrey, GU21 9EE"));

        licenseJson = new LicenseJson(new License("MUZAN1234", "121", "Muzanenhamo", "Artemas",
                "28/03/1990", "United Kingdom", "28/03/2010",
                "28/03/2060", "DVLA", "MUZANK9843ACTK", "001.jpg",
                "27 Foxhill Street, Guildford, Surrey, GU21 9EE"));

        assertThat(licenseJson).isEqualTo(expectedLicenseJson);
        assertThat(licenseJson.toString()).isEqualTo(expectedLicenseJson.toString());
        assertThat(licenseJson.hashCode()).isEqualTo(expectedLicenseJson.hashCode());
    }
}
