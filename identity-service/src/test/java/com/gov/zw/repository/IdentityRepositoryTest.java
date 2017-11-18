package com.gov.zw.repository;

import com.gov.zw.domain.Identity;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class IdentityRepositoryTest {

    @Autowired
    IdentityRepository repository;


    @Test
    public void findIdentityByName(){
        this.repository.save(
                new Identity("", "Artemas", "Muzanenhamo", "28/03/1990",
                        "Mashayamombe", "Harare", "17/11/2017"));

        List<Identity> identities = this.repository.findIdentitiesByName("Artemas");
        Assertions.assertThat(identities.size()).isEqualTo(1);
        Assertions.assertThat(identities.get(0).getName()).isEqualTo("Artemas");
        Assertions.assertThat(identities.get(0).getSurname()).isEqualTo("Muzanenhamo");

    }

}
