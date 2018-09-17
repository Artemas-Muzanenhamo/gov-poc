package com.gov.zw;

import com.gov.zw.domain.License;
import com.gov.zw.repository.LicenseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBSeeder implements CommandLineRunner{

    private final LicenseRepository licenseRepository;

    public DBSeeder(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        License license = new License("MUZAN1234", "121", "Muzanenhamo", "Artemas",
                "28/03/1990", "United Kingdom", "28/03/2010",
                "28/03/2060", "DVLA", "MUZANK9843ACTK", "001.jpg",
                "27 Foxhill Street, Guildford, Surrey, GU21 9EE");

        // delete all data before we put data to use
        this.licenseRepository.deleteAll();

        // add dummy data
        this.licenseRepository.save(license);
    }
}
