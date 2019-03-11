package com.gov.zw.patient;

import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.ServerResponse.created;
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
        return created(URI.create("/patients")).body(patient, Patient.class);
    }

    private static Mono<ServerResponse> defaultWriteResponse(Publisher<Patient> patients) {
        return Mono
                .from(patients)
                .flatMap(p -> created(URI.create("/patients"))
                        .contentType(APPLICATION_JSON_UTF8)
                        .build()
                );
    }

    private static Mono<ServerResponse> defaultReadResponse(Publisher<Patient> patients) {
        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON_UTF8)
                .body(patients, Patient.class);
    }
}
