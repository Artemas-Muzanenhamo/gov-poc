package com.gov.zw.patient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Component
public class DBSeeder implements CommandLineRunner {

    private final PatientRepository patientRepository;

    public DBSeeder(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Flux<Patient> patients =
                Flux
                        .just(
                                new Patient("MUZAN123", "Artemas", "Muzanenhamo",
                                        LocalDate.of(1990, 3, 28),
                                        "68 Jeremy Street, London, W1 7AA"),
                                new Patient("THOMJ123", "Thomas", "Jefferson",
                                        LocalDate.of(1990, 3, 28),
                                        "68 Jeremy Street, London, W1 7AA"),
                                new Patient("MARKS123", "Mark", "Smith",
                                        LocalDate.of(1990, 3, 28),
                                        "68 Jeremy Street, London, W1 7AA")
                        );

        patientRepository.deleteAll();

        patientRepository.saveAll(patients);
    }
}
