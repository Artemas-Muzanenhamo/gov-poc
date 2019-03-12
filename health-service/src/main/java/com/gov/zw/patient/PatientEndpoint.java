package com.gov.zw.patient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PatientEndpoint {

    @Bean
    RouterFunction<ServerResponse> patientRoutes(PatientHandler patientHandler) {
        return route(
                GET("/patients"),
                patientHandler::listAllPatients
        ).andRoute(
                PUT("/patients"),
                patientHandler::addPatient
        ).andRoute(
                POST("/patients"),
                patientHandler::updatePatient
        );
    }
}
