package com.gov.zw.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LicenseJsonMapperTest {

    private LicenseJsonMapper licenseJsonMapper;

    @Before
    public void init() {
        licenseJsonMapper = new LicenseJsonMapper();
    }

    @Test
    public void should_map_license_json_to_a_license_object() {
        License expectedLicense = new License("MUZAN1234", "121", "Muzanenhamo", "Artemas",
                "28/03/1990", "United Kingdom", "28/03/2010",
                "28/03/2060", "DVLA", "MUZANK9843ACTK", "001.jpg",
                "27 Foxhill Street, Guildford, Surrey, GU21 9EE");
        LicenseJson licenseJson = new LicenseJson(expectedLicense);

        License license = licenseJsonMapper.toDto(licenseJson);

        assertThat(license).isEqualTo(expectedLicense);
    }
}
