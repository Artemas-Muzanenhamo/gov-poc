package com.gov.zw.patient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static reactor.core.publisher.Mono.just;

@RunWith(SpringRunner.class)
@WebFluxTest({PatientEndpoint.class, PatientHandler.class})
public class PatientEndpointTest {

    private static final String ALL_PATIENTS_URI = "http://localhost:8080/patients";

    @Autowired
    private WebTestClient client;
    @MockBean
    private PatientService patientService;

    private final Patient patient1 = new Patient("MUZAN123", "Artemas", "Muzanenhamo",
            LocalDate.of(1990, 3, 28), "68 Jeremy Street, London, W1 7AA");
    private final Patient patient2 = new Patient("MUZAN123", "Artemas", "Muzanenhamo",
            LocalDate.of(1990, 3, 28), "68 Jeremy Street, London, W1 7AA");

    @Test
    public void should_return_all_mocked_patients() {
        when(patientService.getAllPatients())
                .thenReturn(Flux.just(patient1, patient2));

        client
                .get()
                .uri(ALL_PATIENTS_URI)
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
    public void should_return_200_when_retrieving_all_patients() {
        when(patientService.getAllPatients())
                .thenReturn(Flux.empty());

        client
                .get()
                .uri(ALL_PATIENTS_URI)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void should_add_patient_given_the_user_has_a_valid_identity() {
        Patient patient =
                new Patient(
                        "12345",
                        "Arty",
                        "Muza",
                        LocalDate.of(1990, 3, 28),
                        "Flat 7, Elm Rose Road, E16 9AA"
                );

        when(patientService.addPatient(just(patient))).thenReturn(just(patient));

        client
                .put()
                .uri(ALL_PATIENTS_URI)
                .body(just(patient), Patient.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }
}
