package com.gov.zw.repository;

import com.gov.zw.dto.Identity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class IdentityRepositoryTest {

    private static final int EXPECT_ONE = 1;
    private static final int EXPECT_TWO = 2;
    private static final int EXPECT_FOUR = 4;
    private static final int FIRST_INDEX = 0;
    private List<Identity> identities = new ArrayList<>(Arrays.asList(
            new Identity("2", "2", "Terrence", "Munhengu", "15/04/1980",
                    "Murehwa", "Mutare", "17/11/2017"),
            new Identity("3", "3", "Tichaona", "Chimuchero", "07/12/1960",
                    "Gutu", "Goromhonzi", "17/11/2017"),
            new Identity("4", "4", "Zindoga", "Ncube", "08/01/1976",
                    "Mhondoro", "Harare", "17/11/2017")
    ));

    @Autowired
    private IdentityRepository repository;

    @BeforeEach
    void saveId() {
        this.repository.save(
                new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                        "Mashayamombe", "Harare", "17/11/2017"));
    }

    @AfterEach
    void purgeIdentities() {
        this.repository.deleteAll();
    }

    @Test
    void addIdentity() {
        this.repository.save(new Identity("2", "2", "Takudzwa", "Mutongi", "27/01/1987",
                "Mashayamombe", "Harare", "17/11/2017"));

        assertThat(this.repository.findAll().size()).isEqualTo(EXPECT_TWO);
    }

    @Test
    void findIdentitiesByName() {

        List<Identity> identities = this.repository.findIdentitiesByName("Artemas");
        assertThat(identities.size()).isEqualTo(EXPECT_ONE);
        assertThat(identities.get(FIRST_INDEX).getName()).isEqualTo("Artemas");
        assertThat(identities.get(FIRST_INDEX).getSurname()).isEqualTo("Muzanenhamo");

    }

    @Test
    void findIdentitiesBySurname() {

        List<Identity> identities = this.repository.findIdentitiesBySurname("Muzanenhamo");
        assertThat(identities.size()).isEqualTo(EXPECT_ONE);
        assertThat(identities.get(FIRST_INDEX).getName()).isEqualTo("Artemas");
        assertThat(identities.get(FIRST_INDEX).getSurname()).isEqualTo("Muzanenhamo");

    }

    @Test
    void findIdentitiesByVillageOfOrigin() {
        List<Identity> identities = this.repository.findIdentitiesByVillageOfOrigin("Mashayamombe");
        assertThat(identities.size()).isEqualTo(EXPECT_ONE);
        assertThat(identities.get(FIRST_INDEX).getName()).isEqualTo("Artemas");
        assertThat(identities.get(FIRST_INDEX).getSurname()).isEqualTo("Muzanenhamo");
    }

    @Test
    void findIdentitiesByNameSurnameAndVillageOfOrigin() {
        List<Identity> identities = this.repository.findIdentitiesByNameAndSurnameAndVillageOfOrigin(
                "Artemas", "Muzanenhamo", "Mashayamombe");
        assertThat(identities.size()).isEqualTo(EXPECT_ONE);
        assertThat(identities.get(FIRST_INDEX).getName()).isEqualTo("Artemas");
        assertThat(identities.get(FIRST_INDEX).getSurname()).isEqualTo("Muzanenhamo");
    }

    @Test
    void findIdentityByIdReferenceNumber() {
        Identity identity = this.repository.findIdentityByIdentityRef("1");
        assertThat(identity.getName()).isEqualTo("Artemas");
        assertThat(identity.getSurname()).isEqualTo("Muzanenhamo");
    }

    @Test
    void findAllIdentities() {
        List<Identity> identityList = new ArrayList<>();

        identities
                .forEach(identity -> this.repository.save(identity));

        this.repository.findAll().forEach(identityList::add);
        assertThat(identityList.size()).isEqualTo(EXPECT_FOUR);
        assertThat(identityList.get(FIRST_INDEX).getSurname()).isEqualTo("Muzanenhamo");
    }

    @Test
    void updateIdentity() {
        Identity identity = new Identity("1", "1", "Takudzwa", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017");
        this.repository.save(identity);
        assertThat(this.repository.findAll().size()).isEqualTo(EXPECT_ONE);
        assertThat(this.repository.findAll().get(FIRST_INDEX).getName()).isEqualTo("Takudzwa");
    }

    @Test
    void deleteIdentity() throws Exception {
        Identity identity = new Identity("2", "2", "Takudzwa", "Mutongi", "27/01/1987",
                "Mashayamombe", "Harare", "17/11/2017");
        this.repository.save(identity);
        this.repository.delete(identity);
        assertThat(this.repository.findAll().size()).isEqualTo(EXPECT_ONE);
    }


}
