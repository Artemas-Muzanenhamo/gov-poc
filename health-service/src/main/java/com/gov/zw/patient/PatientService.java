package com.gov.zw.patient;

import reactor.core.publisher.Flux;

interface PatientService {
    Flux<Patient> getAllPatients();
}
