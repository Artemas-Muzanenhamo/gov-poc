package com.gov.zw.patient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PatientEndpoint {

    private static final String PATIENTS_URI = "/patients";
    private static final String PATIENT_URI = "/patients/{id}";

    @Bean
    RouterFunction<ServerResponse> patientRoutes(PatientHandler patientHandler) {
        return route(
                GET(PATIENTS_URI),
                patientHandler::listAllPatients
        ).andRoute(
                GET(PATIENT_URI),
                patientHandler::getPatient
        ).andRoute(
                PUT(PATIENTS_URI).and(contentType(APPLICATION_JSON_UTF8).and(accept(APPLICATION_JSON_UTF8))),
                patientHandler::addPatient
        ).andRoute(
                POST(PATIENTS_URI).and(contentType(APPLICATION_JSON_UTF8).and(accept(APPLICATION_JSON_UTF8))),
                patientHandler::updatePatient
        );
    }
}
