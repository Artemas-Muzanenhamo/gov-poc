package com.gov.zw.patient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@WebFluxTest
@RunWith(SpringRunner.class)
@Import(PatientConfiguration.class)
public class PatientEndPointTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private PatientService patientService;

    private static final String ALLPATIENTSURI = "http://localhost:8080/patients";

    private final Patient patient1 = new Patient("Artemas", "Muzanenhamo", LocalDate.of(1990, 3, 28),
            "MUZAN123", "68 Jeremy Street, London, W1 7AA");
    private final Patient patient2 = new Patient("Artemas", "Muzanenhamo", LocalDate.of(1990, 3, 28),
            "MUZAN123", "68 Jeremy Street, London, W1 7AA");

    @Test
    public void shouldReturnAllMockedPatients() {
        when(patientService.getAllPatients())
                .thenReturn(Flux.just(patient1, patient2));

        client
                .get()
                .uri(ALLPATIENTSURI)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("@.[0].name").isEqualTo("Artemas")
                .jsonPath("@.[0].surname").isEqualTo("Muzanenhamo")
                .jsonPath("@.[0].identityRef").isEqualTo("MUZAN123")
                .jsonPath("@.[0].address").isEqualTo("68 Jeremy Street, London, W1 7AA");
    }

    @Test
    public void shouldReturn200WhenRetrievingAllPatients() {
        when(patientService.getAllPatients())
                .thenReturn(Flux.empty()); // revise

        client
                .get()
                .uri(ALLPATIENTSURI)
                .exchange()
                .expectStatus().isOk();
    }
}
