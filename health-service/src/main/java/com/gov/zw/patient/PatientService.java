package com.gov.zw.patient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

interface PatientService {
    Flux<Patient> getAllPatients();

    Mono<Patient> addPatient(Mono<Patient> patientMono);
}
