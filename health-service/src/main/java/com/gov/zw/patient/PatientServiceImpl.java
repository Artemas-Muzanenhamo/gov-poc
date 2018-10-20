package com.gov.zw.patient;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Service
public class PatientServiceImpl implements PatientService {

    Patient patient = new Patient("Artemas", "Muzanenhamo", LocalDate.of(1990, 3, 28),
            "MUZAN123", "68 Jeremy Street, London, W1 7AA");
    Flux<Patient> patientFlux = Flux.just(patient);

    @Override
    public Flux<Patient> getAllPatients() {
        return patientFlux;
    }
}
