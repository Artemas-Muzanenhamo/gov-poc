package com.gov.zw.doctor;

import com.gov.zw.patient.PatientEndPoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;

@WebFluxTest
@RunWith(SpringRunner.class)
@Import(PatientEndPoint.class)
public class DoctorEndPointTest {

    private final Doctor doctor = new Doctor("Donald", "Smith", Arrays.asList("practice1", "practice2"), "DSmith01");

    @Autowired
    private WebTestClient webClient;

    @Test
    public void shouldReturnAllDoctors() {
        // TODO - add endpoint tests for doctor endpoint
    }
}
