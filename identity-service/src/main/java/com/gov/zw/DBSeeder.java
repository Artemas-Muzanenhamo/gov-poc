package com.gov.zw;

import com.gov.zw.domain.Identity;
import com.gov.zw.repository.IdentityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBSeeder implements CommandLineRunner {

    private final IdentityRepository identityRepository;

    public DBSeeder(IdentityRepository identityRepository) {
        this.identityRepository = identityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Identity identity = new Identity("1", "121", "Artemas", "Muzanenhamo", "28/03/1990",
            "Mashayamombe", "Harare", "17/11/2017");

        // delete data before loading data on startup
        this.identityRepository.deleteAll();

        // load data on startup
        this.identityRepository.save(identity);
    }
}
