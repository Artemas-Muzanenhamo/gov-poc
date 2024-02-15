package com.gov.zw.repository;

import com.gov.zw.dto.License;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class LicenseRepositoryTest {

    @Autowired
    private LicenseRepository licenseRepository;

    @BeforeEach
    void addToRepository() {
        this.licenseRepository.deleteAll();

        List<License> licenseList = Arrays.asList(
                new License("1", "1", "Rodgers", "Mike Oscar", "28/03/1990", "ZIM",
                        "23/11/2017", "22/11/2027", "ZDVLA", "MUZANEN123456ABCDEF",
                        "01.jpg", "123 Glendale, Harare, Zimbabwe"),
                new License("2", "2", "Juliet", "Bravo Zulu", "28/03/1990", "ZIM",
                        "23/11/2017", "22/11/2027", "ZDVLA", "MUZANEN123456ABCDEF",
                        "01.jpg", "123 Glendale, Harare, Zimbabwe"),
                new License("3", "3", "Foxtrot", "Juliet Papa", "28/03/1990", "ZIM",
                        "23/11/2017", "22/11/2027", "ZDVLA", "MUZANEN123456ABCDEF",
                        "01.jpg", "123 Glendale, Harare, Zimbabwe"),
                new License("4", "4", "Yankee", "Xray Whiskey", "28/03/1990", "ZIM",
                        "23/11/2017", "22/11/2027", "ZDVLA", "MUZANEN123456ABCDEF",
                        "01.jpg", "123 Glendale, Harare, Zimbabwe")
        );

        this.licenseRepository.insert(licenseList);
    }

    @AfterEach
    void purgeRepository() {
        this.licenseRepository.deleteAll();
    }

    @Test
    void addLicense() {
        License license = new License("5", "5", "James", "Lebron", "28/03/1990", "ZIM",
                "23/11/2017", "22/11/2027", "ZDVLA", "MUZANEN123456ABCDEF",
                "01.jpg", "123 Glendale, Harare, Zimbabwe");

        this.licenseRepository.save(license);

        List<License> licenses = this.licenseRepository.findAll();
        Assertions.assertThat(licenses.get(4).getFirstNames()).isEqualTo("Lebron");
        Assertions.assertThat(licenses.size()).isEqualTo(5);
    }

    @Test
    void findAllLicenses() {
        List<License> licenses = this.licenseRepository.findAll();

        Assertions.assertThat(licenses.size()).isEqualTo(4);
        Assertions.assertThat(licenses.get(0).getAgency()).isEqualTo("ZDVLA");
        Assertions.assertThat(licenses.get(0).getDateOfBirth()).isEqualTo("28/03/1990");
    }

    @Test
    void updateLicense() {
        License license = new License("4", "4", "Charlie", "Delta Golf", "28/03/1990", "ZIM",
                "23/11/2017", "22/11/2027", "ZDVLA", "MUZANEN123456ABCDEF",
                "01.jpg", "123 Glendale, Harare, Zimbabwe");

        this.licenseRepository.save(license);

        List<License> licenses = this.licenseRepository.findAll();
        Assertions.assertThat(licenses.size()).isEqualTo(4);
        Assertions.assertThat(licenses.get(3).getSurname()).isEqualTo("Charlie");
        Assertions.assertThat(licenses.get(3).getFirstNames()).isEqualTo("Delta Golf");
    }

    @Test
    void deleteLicense() {
        this.licenseRepository.deleteById("1");
        List<License> licenses = this.licenseRepository.findAll();

        Assertions.assertThat(licenses.get(0).getFirstNames()).isEqualTo("Bravo Zulu");
        Assertions.assertThat(licenses.size()).isEqualTo(3);
    }

}
