package com.gov.zw.patient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

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
        Mono<Patient> patient = request.bodyToMono(Patient.class)
                .flatMap(patientServiceImpl::addPatient);
        return created(URI.create("/patients")).body(patient, Patient.class);
    }

    Mono<ServerResponse> updatePatient(ServerRequest request) {
        Mono<Patient> patientMono = request.bodyToMono(Patient.class)
                .flatMap(patientServiceImpl::updatePatient);
        return ok().body(patientMono, Patient.class);
    }

    Mono<ServerResponse> deletePatient(ServerRequest request) {
        // TODO: add tests for this...
        int patientId = Integer.valueOf(request.pathVariable("identityRef"));
        return patientServiceImpl.getPatient(patientId)
                .flatMap(patient -> ok().contentType(APPLICATION_JSON_UTF8).body(fromObject(patient)))
                .switchIfEmpty(notFound().build());
    }
}
