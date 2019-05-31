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

    @Bean
    RouterFunction<ServerResponse> patientRoutes(PatientHandler patientHandler) {
        return route(
                GET(PATIENTS_URI),
                patientHandler::listAllPatients
        ).andRoute(
                PUT(PATIENTS_URI).and(contentType(APPLICATION_JSON_UTF8).and(accept(APPLICATION_JSON_UTF8))),
                patientHandler::addPatient
        ).andRoute(
                POST(PATIENTS_URI).and(contentType(APPLICATION_JSON_UTF8).and(accept(APPLICATION_JSON_UTF8))),
                patientHandler::updatePatient
        ).andRoute(
                DELETE(PATIENTS_URI),
                patientHandler::deletePatient
        );
    }
}
