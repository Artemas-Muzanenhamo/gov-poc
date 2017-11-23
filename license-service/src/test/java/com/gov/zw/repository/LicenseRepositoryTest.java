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
                new License("Muzanenhamo", "Artemas Takudzwa", "28/03/1990", "ZIM",
                        "23/11/2017", "22/11/2027", "ZDVLA", "MUZANEN123456ABCDEF",
                        "01.jpg", "123 Glendale, Harare, Zimbabwe"),
                new License("Muzanenhamo", "Artemas Takudzwa", "28/03/1990", "ZIM",
                        "23/11/2017", "22/11/2027", "ZDVLA", "MUZANEN123456ABCDEF",
                        "01.jpg", "123 Glendale, Harare, Zimbabwe"),
                new License("Muzanenhamo", "Artemas Takudzwa", "28/03/1990", "ZIM",
                        "23/11/2017", "22/11/2027", "ZDVLA", "MUZANEN123456ABCDEF",
                        "01.jpg", "123 Glendale, Harare, Zimbabwe"),
                new License("Muzanenhamo", "Artemas Takudzwa", "28/03/1990", "ZIM",
                        "23/11/2017", "22/11/2027", "ZDVLA", "MUZANEN123456ABCDEF",
                        "01.jpg", "123 Glendale, Harare, Zimbabwe")
        );

        this.licenseRepository.save(licenseList);
    }

//    @After
//    public void purgeRepository(){
//        this.licenseRepository.deleteAll();
//    }

    @Test
    public void findAllLicenses(){
        List<License> licenses = this.licenseRepository.findAll();
        Assertions.assertThat(licenses.size()).isEqualTo(4);
        Assertions.assertThat(licenses.get(0).getAgency()).isEqualTo("ZDVLA");
        Assertions.assertThat(licenses.get(0).getDateOfBirth()).isEqualTo("28/03/1990");
    }

}
