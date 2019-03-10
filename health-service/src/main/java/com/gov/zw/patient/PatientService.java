package com.gov.zw.patient;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

interface PatientService {
    Flux<Patient> getAllPatients();

    Publisher<Void> addPatient(Mono<Patient> patientMono);
}
