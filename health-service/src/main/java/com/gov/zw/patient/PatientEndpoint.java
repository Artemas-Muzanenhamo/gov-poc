package com.gov.zw.patient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class PatientEndpoint {

    @Bean
    RouterFunction<ServerResponse> patientRoutes(PatientService patientServiceImpl) {
        return route(
                GET("/patients"),
                request -> ok().body(patientServiceImpl.getAllPatients(), Patient.class)
        ).andRoute(
                PUT("/patients").and(accept(APPLICATION_JSON).and(contentType(APPLICATION_JSON))),
                request -> {
                    Mono<Patient> patientMono = request.bodyToMono(Patient.class);
                    request.bodyToMono(Patient.class);
                    // TODO: call patientServiceImpl.addPatient(patientMono(Patient)
                    return ok().build();
                });
    }
}
