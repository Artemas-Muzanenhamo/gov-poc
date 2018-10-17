package com.gov.zw.patient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@WebFluxTest
public class PatientEndPointTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void shouldGetAllPatients() throws Exception {
        webClient.get().uri("/patient")
                .exchange()
                .expectStatus().isOk();
    }
}
