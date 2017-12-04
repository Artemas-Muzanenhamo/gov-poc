package com.gov.zw.repository;

import com.gov.zw.domain.License;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@DataMongoTest
@RunWith(SpringRunner.class)
public class LicenseRepositoryTest {

    @Autowired
    private LicenseRepository licenseRepository;

    @Before
    public void addToRepository(){
        List<License> licenseList = Arrays.asList(
                new License("1","Rodgers", "Mike Oscar", "28/03/1990", "ZIM",
                        "23/11/2017", "22/11/2027", "ZDVLA", "MUZANEN123456ABCDEF",
                        "01.jpg", "123 Glendale, Harare, Zimbabwe"),
                new License("2","Juliet", "Bravo Zulu", "28/03/1990", "ZIM",
                        "23/11/2017", "22/11/2027", "ZDVLA", "MUZANEN123456ABCDEF",
                        "01.jpg", "123 Glendale, Harare, Zimbabwe"),
                new License("3","Foxtrot", "Juliet Papa", "28/03/1990", "ZIM",
                        "23/11/2017", "22/11/2027", "ZDVLA", "MUZANEN123456ABCDEF",
                        "01.jpg", "123 Glendale, Harare, Zimbabwe"),
                new License("4","Yankee", "Xray Whiskey", "28/03/1990", "ZIM",
                        "23/11/2017", "22/11/2027", "ZDVLA", "MUZANEN123456ABCDEF",
                        "01.jpg", "123 Glendale, Harare, Zimbabwe")
        );

        this.licenseRepository.save(licenseList);
    }

    @After
    public void purgeRepository(){
        this.licenseRepository.deleteAll();
    }

    @Test
    public void addLicense(){
        License license = new License("5","James", "Lebron", "28/03/1990", "ZIM",
                "23/11/2017", "22/11/2027", "ZDVLA", "MUZANEN123456ABCDEF",
                "01.jpg", "123 Glendale, Harare, Zimbabwe");
        this.licenseRepository.save(license);
        List<License> licenses = this.licenseRepository.findAll();
        Assertions.assertThat(licenses.get(4).getFirstNames()).isEqualTo("Lebron");
        Assertions.assertThat(licenses.size()).isEqualTo(5);
    }

    @Test
    public void findAllLicenses(){
        List<License> licenses = this.licenseRepository.findAll();
        Assertions.assertThat(licenses.size()).isEqualTo(4);
        Assertions.assertThat(licenses.get(0).getAgency()).isEqualTo("ZDVLA");
        Assertions.assertThat(licenses.get(0).getDateOfBirth()).isEqualTo("28/03/1990");
    }

    @Test
    public void updateLicense(){
        License license = new License("4","Charlie", "Delta Golf", "28/03/1990", "ZIM",
                "23/11/2017", "22/11/2027", "ZDVLA", "MUZANEN123456ABCDEF",
                "01.jpg", "123 Glendale, Harare, Zimbabwe");
        this.licenseRepository.save(license);
        List<License> licenses = this.licenseRepository.findAll();
        Assertions.assertThat(licenses.size()).isEqualTo(4);
        Assertions.assertThat(licenses.get(3).getSurname()).isEqualTo("Charlie");
        Assertions.assertThat(licenses.get(3).getFirstNames()).isEqualTo("Delta Golf");
    }

    @Test
    public void deleteLicense(){
        this.licenseRepository.delete("1");
        List<License> licenses = this.licenseRepository.findAll();

        Assertions.assertThat(licenses.get(0).getFirstNames()).isEqualTo("Bravo Zulu");
        Assertions.assertThat(licenses.size()).isEqualTo(3);
    }

}
