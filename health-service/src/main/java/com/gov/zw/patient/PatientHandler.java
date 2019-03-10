package com.gov.zw.patient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class PatientHandler {
    private final PatientService patientServiceImpl;

    public PatientHandler(PatientService patientServiceImpl) {
        this.patientServiceImpl = patientServiceImpl;
    }

    Mono<ServerResponse> listAllPatients(ServerRequest request) {
        Flux<Patient> patients = patientServiceImpl.getAllPatients();
        return ok().body(patients, Patient.class);
    }

    Mono<ServerResponse> addPatient(ServerRequest request) {
        Mono<Patient> patient = request.bodyToMono(Patient.class);
        return ok().build(patientServiceImpl.addPatient(patient));
    }
}
