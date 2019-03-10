package com.gov.zw.patient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PatientEndpoint {

    @Bean
    RouterFunction<ServerResponse> patientRoutes(PatientHandler patientHandler) {
        return route(
                GET("/patients"),
                patientHandler::listAllPatients
        );
//                .andRoute(
//                PUT("/patients").and(accept(APPLICATION_JSON).and(contentType(APPLICATION_JSON))),
//                request -> ok().body(patientServiceImpl.addPatient(request.bodyToMono(Patient.class)), Patient.class)
//        );
    }
}
