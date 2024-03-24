package com.gov.zw.patient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PatientEndpoint {

    private static final String PATIENTS_PATH = "/patients";
    private static final String PATIENT_BY_ID_PATH = "/patients/{id}";

    @Bean
    RouterFunction<ServerResponse> patientRoutes(PatientHandler patientHandler) {
        return route(
                GET(PATIENTS_PATH),
                patientHandler::listAllPatients
        ).andRoute(
                GET(PATIENT_BY_ID_PATH),
                patientHandler::getPatient
        ).andRoute(
                PUT(PATIENTS_PATH).and(contentType(APPLICATION_JSON).and(accept(APPLICATION_JSON))),
                patientHandler::addPatient
        ).andRoute(
                POST(PATIENTS_PATH).and(contentType(APPLICATION_JSON).and(accept(APPLICATION_JSON))),
                patientHandler::updatePatient
        ).andRoute(
                DELETE(PATIENT_BY_ID_PATH),
                patientHandler::deletePatient
        );
    }
}
