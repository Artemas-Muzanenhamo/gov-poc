package com.gov.zw.patient;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

interface PatientService {
    Flux<Patient> getAllPatients();

    void addPatient(Mono<Patient> patientMono);
}
