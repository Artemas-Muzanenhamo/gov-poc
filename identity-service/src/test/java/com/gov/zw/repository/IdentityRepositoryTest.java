package com.gov.zw.repository;

import com.gov.zw.domain.Identity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
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
    private final List<Identity> identities = new ArrayList<>(Arrays.asList(
            new Identity("2", "2", "Terrence", "Munhengu", "15/04/1980",
                    "Murehwa", "Mutare", "17/11/2017"),
            new Identity("3", "3", "Tichaona", "Chimuchero", "07/12/1960",
                    "Gutu", "Goromhonzi", "17/11/2017"),
            new Identity("4", "4", "Zindoga", "Ncube", "08/01/1976",
                    "Mhondoro", "Harare", "17/11/2017")
    ));

    @Autowired
    private IdentityRepository repository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void saveId() {
        mongoTemplate.getDb().drop();
        mongoTemplate.insert(new Identity("1", "1", "Artemas", "Muzanenhamo",
                "28/03/1990", "Mashayamombe", "Harare", "17/11/2017"));
    }

    @Test
    void addIdentity() {
        mongoTemplate.insert(new Identity("2", "2", "Takudzwa", "Mutongi", "27/01/1987",
                "Mashayamombe", "Harare", "17/11/2017"));

        assertThat(this.repository.findAll()).hasSize(EXPECT_TWO);
    }

    @Test
    void findIdentitiesByName() {
        List<Identity> identitiesByName = this.repository.findIdentitiesByName("Artemas");

        assertThat(identitiesByName).hasSize(EXPECT_ONE)
                .first()
                .extracting(Identity::name, Identity::surname)
                .containsExactly("Artemas", "Muzanenhamo");

    }

    @Test
    void findIdentitiesBySurname() {
        List<Identity> identitiesBySurname = this.repository.findIdentitiesBySurname("Muzanenhamo");

        assertThat(identitiesBySurname).hasSize(EXPECT_ONE)
                .first()
                .extracting(Identity::name, Identity::surname)
                .containsExactly("Artemas", "Muzanenhamo");

    }

    @Test
    void findIdentitiesByVillageOfOrigin() {
        List<Identity> identitiesByVillageOfOrigin = this.repository.findIdentitiesByVillageOfOrigin("Mashayamombe");

        assertThat(identitiesByVillageOfOrigin).hasSize(EXPECT_ONE)
                .first()
                .extracting(Identity::name, Identity::surname)
                .containsExactly("Artemas", "Muzanenhamo");
    }

    @Test
    void findIdentitiesByNameSurnameAndVillageOfOrigin() {
        List<Identity> identitiesByNameAndSurnameAndVillageOfOrigin = this.repository.findIdentitiesByNameAndSurnameAndVillageOfOrigin(
                "Artemas", "Muzanenhamo", "Mashayamombe");

        assertThat(identitiesByNameAndSurnameAndVillageOfOrigin).hasSize(EXPECT_ONE)
                .first()
                .extracting(Identity::name, Identity::surname)
                .containsExactly("Artemas", "Muzanenhamo");
    }

    @Test
    void findIdentityByIdReferenceNumber() {
        Identity identity = this.repository.findIdentityByIdentityRef("1");

        assertThat(identity).isNotNull()
                .extracting(Identity::name, Identity::surname)
                .containsExactly("Artemas", "Muzanenhamo");
    }

    @Test
    void findAllIdentities() {
        this.repository.saveAll(identities);

        List<Identity> identityList = new ArrayList<>(this.repository.findAll());

        assertThat(identityList).hasSize(EXPECT_FOUR)
                .first()
                .extracting(Identity::surname)
                .isEqualTo("Muzanenhamo");
    }

    @Test
    void updateIdentity() {
        Identity identity = new Identity("1", "1", "Takudzwa", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017");
        this.repository.save(identity);

        assertThat(this.repository.findAll()).hasSize(EXPECT_ONE)
                .first()
                .extracting(Identity::name)
                .isEqualTo("Takudzwa");
    }

    @Test
    void deleteIdentity() {
        Identity identity = new Identity("2", "2", "Takudzwa", "Mutongi", "27/01/1987",
                "Mashayamombe", "Harare", "17/11/2017");

        this.repository.save(identity);
        this.repository.delete(identity);

        assertThat(this.repository.findAll()).hasSize(EXPECT_ONE);
    }


}
