package com.gov.zw.patient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class PatientConfiguration {

    @Bean
    RouterFunction<?> patientRoutes(PatientService patientServiceImpl) {
        return route(GET("/patients"),
                request -> ok().body(patientServiceImpl.getAllPatients(), Patient.class)
        );
    }
}
