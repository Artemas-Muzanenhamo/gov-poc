package com.gov.zw.patient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

interface PatientService {
    Flux<Patient> getAllPatients();

    Mono<Patient> addPatient(Patient patientMono);

    Mono<Patient> updatePatient(Patient updatedPatientMono);

    Mono<Patient> getPatient(Optional<String> patientId);
}
