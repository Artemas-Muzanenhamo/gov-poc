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

import static java.lang.Integer.valueOf;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
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
        given(patientService.getAllPatients())
                .willReturn(Flux.just(patient1, patient2));

        client
                .get()
                .uri(ALL_PATIENTS_URI)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("@.[0].name").isEqualTo("Artemas")
                .jsonPath("@.[0].surname").isEqualTo("Muzanenhamo")
                .jsonPath("@.[0].identityRef").isEqualTo("MUZAN123")
                .jsonPath("@.[0].address").isEqualTo("68 Jeremy Street, London, W1 7AA");
    }

    @Test
    public void should_return_200_when_retrieving_all_patients() {
        given(patientService.getAllPatients())
                .willReturn(Flux.empty());

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

        given(patientService.addPatient(patient)).willReturn(just(patient));

        client
                .put()
                .uri(ALL_PATIENTS_URI)
                .body(just(patient), Patient.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    public void should_update_existing_patient() {
        Patient patient =
                new Patient(
                        "12345",
                        "Arty",
                        "Muza",
                        LocalDate.of(1990, 3, 28),
                        "Flat 7, Elm Rose Road, E16 9AA"
                );

        given(patientService.updatePatient(patient)).willReturn(just(patient));

        client
                .post()
                .uri(ALL_PATIENTS_URI)
                .body(just(patient), Patient.class)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void should_get_an_existing_patient() {
        // TODO: Fix this
        Patient patient =
                new Patient(
                        "12345",
                        "Arty",
                        "Muza",
                        LocalDate.of(1990, 3, 28),
                        "Flat 7, Elm Rose Road, E16 9AA"
                );

        given(patientService.getPatient(valueOf(patient.getIdentityRef()))).willReturn(just(patient));
//        given(patientService.deletePatient(just(patient))).willReturn(just(patient).then());

        client
                .get()
                .uri(ALL_PATIENTS_URI+ "/" + patient.getIdentityRef())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("@.name").isEqualTo("Arty")
                .jsonPath("@.surname").isEqualTo("Muza")
                .jsonPath("@.identityRef").isEqualTo("12345")
                .jsonPath("@.address").isEqualTo("Flat 7, Elm Rose Road, E16 9AA");
    }
}
