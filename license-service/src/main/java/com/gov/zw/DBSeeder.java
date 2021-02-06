package com.gov.zw;

import com.gov.zw.dto.License;
import com.gov.zw.repository.LicenseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DBSeeder implements CommandLineRunner{

    private final LicenseRepository licenseRepository;

    public DBSeeder(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<License> licenses = Arrays.asList(
                new License("MUZAN1234", "121", "Muzanenhamo", "Artemas",
                        "28/03/1990", "United Kingdom", "28/03/2010",
                        "28/03/2060", "DVLA", "MUZANK9843ACTK", "001.jpg",
                        "27 Foxhill Street, Guildford, Surrey, GU21 9EE"),
                new License("TONYM1234", "131", "Morretti", "Thomas",
                        "15/01/1980", "United Kingdom", "28/03/2010",
                        "28/03/2060", "DVLA", "MUZANK9843ACTK", "001.jpg",
                        "27 Foxhill Street, Guildford, Surrey, GU21 9EE"),
                new License("MATTS1234", "141", "Smith", "Matthew",
                        "14/08/1950", "United Kingdom", "28/03/2010",
                        "28/03/2060", "DVLA", "MUZANK9843ACTK", "001.jpg",
                        "27 Foxhill Street, Guildford, Surrey, GU21 9EE"),
                new License("JOHW1234", "151", "Walter", "John",
                        "28/03/1990", "United Kingdom", "28/03/2010",
                        "28/03/2060", "DVLA", "MUZANK9843ACTK", "001.jpg",
                        "27 Foxhill Street, Guildford, Surrey, GU21 9EE"),
                new License("ROBJ1234", "161", "Jenkins", "Rob",
                        "28/03/1990", "United Kingdom", "28/03/2010",
                        "28/03/2060", "DVLA", "MUZANK9843ACTK", "001.jpg",
                        "27 Foxhill Street, Guildford, Surrey, GU21 9EE")
        );

        // delete all data before we put data to use
        this.licenseRepository.deleteAll();

        // add dummy data
        this.licenseRepository.saveAll(licenses);
    }
}
