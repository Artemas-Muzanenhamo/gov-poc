package com.gov.zw;

import com.gov.zw.domain.Identity;
import com.gov.zw.repository.IdentityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DBSeeder implements CommandLineRunner {

    private final IdentityRepository identityRepository;

    public DBSeeder(IdentityRepository identityRepository) {
        this.identityRepository = identityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Identity> identity = Arrays.asList(
                new Identity("1", "121", "Artemas", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017"),
                new Identity("2", "122", "Artemas", "Muzanenhamo", "28/03/1990",
                        "Mashayamombe", "Harare", "17/11/2017"),
                new Identity("3", "123", "Artemas", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017"),
                new Identity("4", "124", "Artemas", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017"),
                new Identity("5", "125", "Artemas", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017")
        );

        // delete data before loading data on startup
        this.identityRepository.deleteAll();

        // load data on startup
        this.identityRepository.saveAll(identity);
    }
}
