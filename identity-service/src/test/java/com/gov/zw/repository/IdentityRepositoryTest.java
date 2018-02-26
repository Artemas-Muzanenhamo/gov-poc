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

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class IdentityRepositoryTest {

    public final int EXPECTONE = 1;
    public final int EXPECTTWO = 2;
    public final int EXPECTFOUR = 4;
    public final int FIRSTINDEX = 0;

    @Autowired
    IdentityRepository repository;

    @Before
    public void saveId() {
        this.repository.save(
                new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                        "Mashayamombe", "Harare", "17/11/2017"));
    }

    @After
    public void purgeIdentities() {
        this.repository.deleteAll();
    }

    @Test
    public void addIdentity() {
        this.repository.save(new Identity("2", "2", "Takudzwa", "Mutongi", "27/01/1987",
                "Mashayamombe", "Harare", "17/11/2017"));

        assertThat(this.repository.findAll().size()).isEqualTo(EXPECTTWO);
    }

    @Test
    public void findIdentitiesByName() {

        List<Identity> identities = this.repository.findIdentitiesByName("Artemas");
        assertThat(identities.size()).isEqualTo(EXPECTONE);
        assertThat(identities.get(FIRSTINDEX).getName()).isEqualTo("Artemas");
        assertThat(identities.get(FIRSTINDEX).getSurname()).isEqualTo("Muzanenhamo");

    }

    @Test
    public void findIdentitiesBySurname() {

        List<Identity> identities = this.repository.findIdentitiesBySurname("Muzanenhamo");
        assertThat(identities.size()).isEqualTo(EXPECTONE);
        assertThat(identities.get(FIRSTINDEX).getName()).isEqualTo("Artemas");
        assertThat(identities.get(FIRSTINDEX).getSurname()).isEqualTo("Muzanenhamo");

    }

    @Test
    public void findIdentitiesByVillageOfOrigin() {
        List<Identity> identities = this.repository.findIdentitiesByVillageOfOrigin("Mashayamombe");
        assertThat(identities.size()).isEqualTo(EXPECTONE);
        assertThat(identities.get(FIRSTINDEX).getName()).isEqualTo("Artemas");
        assertThat(identities.get(FIRSTINDEX).getSurname()).isEqualTo("Muzanenhamo");
    }

    @Test
    public void findIdentitiesByNameSurnameAndVillageOfOrigin() {
        List<Identity> identities = this.repository.findIdentitiesByNameAndSurnameAndVillageOfOrigin(
                "Artemas", "Muzanenhamo", "Mashayamombe");
        assertThat(identities.size()).isEqualTo(EXPECTONE);
        assertThat(identities.get(FIRSTINDEX).getName()).isEqualTo("Artemas");
        assertThat(identities.get(FIRSTINDEX).getSurname()).isEqualTo("Muzanenhamo");
    }

    @Test
    public void findIdentityByIdReferenceNumber() {
        Identity identity = this.repository.findIdentityByIdentityRef("1");
        assertThat(identity.getName()).isEqualTo("Artemas");
        assertThat(identity.getSurname()).isEqualTo("Muzanenhamo");
    }

    @Test
    public void findAllIdentities() {
        List<Identity> identityList = new ArrayList<>();

        List<Identity> identities = new ArrayList<>(Arrays.asList(
                new Identity("2", "2", "Terrence", "Munhengu", "15/04/1980",
                        "Murehwa", "Mutare", "17/11/2017"),
                new Identity("3", "3", "Tichaona", "Chimuchero", "07/12/1960",
                        "Gutu", "Goromhonzi", "17/11/2017"),
                new Identity("4", "4", "Zindoga", "Ncube", "08/01/1976",
                        "Mhondoro", "Harare", "17/11/2017")
        ));

        identities.forEach(t -> this.repository.save(t));

        this.repository.findAll().forEach(identityList::add);
        assertThat(identityList.size()).isEqualTo(EXPECTFOUR);
        assertThat(identityList.get(FIRSTINDEX).getSurname()).isEqualTo("Muzanenhamo");
    }

    @Test
    public void updateIdentity() {
        Identity identity = new Identity("1", "1", "Takudzwa", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017");
        this.repository.save(identity);
        assertThat(this.repository.findAll().size()).isEqualTo(EXPECTONE);
        assertThat(this.repository.findAll().get(FIRSTINDEX).getName()).isEqualTo("Takudzwa");
    }

    @Test
    public void deleteIdentity() throws Exception {
        Identity identity = new Identity("2", "2", "Takudzwa", "Mutongi", "27/01/1987",
                "Mashayamombe", "Harare", "17/11/2017");
        this.repository.save(identity);
        this.repository.delete(identity);
        assertThat(this.repository.findAll().size()).isEqualTo(EXPECTONE);
    }


}
