package com.gov.zw.repository;

import com.gov.zw.domain.Identity;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class IdentityRepositoryTest {

    @Autowired
    IdentityRepository repository;

    @Before
    public void saveIdentity(){
        this.repository.save(
                new Identity("Artemas", "Muzanenhamo", "28/03/1990",
                        "Mashayamombe", "Harare", "17/11/2017"));
    }

    @After
    public void purgeIdentities(){
        this.repository.deleteAll();
    }

    @Test
    public void saveId(){
        this.repository.save(new Identity("Takudzwa", "Mutongi", "27/01/1987",
                "Mashayamombe", "Harare", "17/11/2017"));

        Assertions.assertThat(this.repository.findAll().size()).isEqualTo(2);
    }


    @Test
    public void findIdentitiesByName(){

        List<Identity> identities = this.repository.findIdentitiesByName("Artemas");
        Assertions.assertThat(identities.size()).isEqualTo(1);
        Assertions.assertThat(identities.get(0).getName()).isEqualTo("Artemas");
        Assertions.assertThat(identities.get(0).getSurname()).isEqualTo("Muzanenhamo");

    }

    @Test
    public void findIdentitiesBySurname(){

        List<Identity> identities = this.repository.findIdentitiesBySurname("Muzanenhamo");
        Assertions.assertThat(identities.size()).isEqualTo(1);
        Assertions.assertThat(identities.get(0).getName()).isEqualTo("Artemas");
        Assertions.assertThat(identities.get(0).getSurname()).isEqualTo("Muzanenhamo");

    }

    @Test
    public void findIdentitiesByVillageOfOrigin(){
        List<Identity> identities = this.repository.findIdentitiesByVillageOfOrigin("Mashayamombe");
        Assertions.assertThat(identities.size()).isEqualTo(1);
        Assertions.assertThat(identities.get(0).getName()).isEqualTo("Artemas");
        Assertions.assertThat(identities.get(0).getSurname()).isEqualTo("Muzanenhamo");
    }

    @Test
    public void findIdentitiesByNameSurnameAndVillageOfOrigin(){
        List<Identity> identities = this.repository.findIdentitiesByNameAndSurnameAndVillageOfOrigin(
                "Artemas", "Muzanenhamo", "Mashayamombe");
        Assertions.assertThat(identities.size()).isEqualTo(1);
        Assertions.assertThat(identities.get(0).getName()).isEqualTo("Artemas");
        Assertions.assertThat(identities.get(0).getSurname()).isEqualTo("Muzanenhamo");
    }

    @Test
    public void findAllIdentities(){
        List<Identity> identityList = new ArrayList<>();

        List<Identity> identities = new ArrayList<>(Arrays.asList(
                new Identity("Terrence", "Munhengu", "15/04/1980",
                        "Murehwa", "Mutare", "17/11/2017"),
                new Identity("Tichaona", "Chimuchero", "07/12/1960",
                        "Gutu", "Goromhonzi", "17/11/2017"),
                new Identity("Zindoga", "Ncube", "08/01/1976",
                        "Mhondoro", "Harare", "17/11/2017")
        ));

        identities.forEach(t -> this.repository.save(t));

        this.repository.findAll().forEach(identityList :: add);
        Assertions.assertThat(identityList.size()).isEqualTo(4);
        Assertions.assertThat(identityList.get(0).getSurname()).isEqualTo("Muzanenhamo");
    }

}
