package com.gov.zw.patient;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.Optional;

import static java.lang.String.format;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static reactor.core.publisher.Mono.just;

@ExtendWith(SpringExtension.class)
@WebFluxTest({PatientEndpoint.class, PatientHandler.class})
class PatientEndpointTest {

    private static final String ALL_PATIENTS_URL = "http://localhost:8080/patients";
    private static final String PATIENT_URL = "http://localhost:8080/patients/%s";
    private static final String IDENTITY_REF = "MUZAN123";
    private static final String NAME = "Artemas";
    private static final String SURNAME = "Muzanenhamo";
    private static final LocalDate DATE_OF_BIRTH = LocalDate.of(1990, 3, 28);
    private static final String ADDRESS = "68 Jeremy Street, London, W1 7AA";
    private final Patient patient1 = new Patient(IDENTITY_REF, NAME, SURNAME, DATE_OF_BIRTH, ADDRESS);
    private final Patient patient2 = new Patient(IDENTITY_REF, NAME, SURNAME, DATE_OF_BIRTH, ADDRESS);

    @Autowired
    private WebTestClient client;
    @MockBean
    private PatientService patientService;

    @Test
    @DisplayName("Should return all patients")
    void getAllPatients() {
        given(patientService.getAllPatients())
                .willReturn(Flux.just(patient1, patient2));

        client
                .get()
                .uri(ALL_PATIENTS_URL)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("@.[0].name").isEqualTo(NAME)
                .jsonPath("@.[0].surname").isEqualTo(SURNAME)
                .jsonPath("@.[0].identityRef").isEqualTo(IDENTITY_REF)
                .jsonPath("@.[0].address").isEqualTo(ADDRESS);
    }

    @Test
    @DisplayName("Should return HTTP_STATUS OK when retrieving all patients")
    void returnHttpStatus200RetrivingAllPatients() {
        given(patientService.getAllPatients())
                .willReturn(Flux.empty());

        client
                .get()
                .uri(ALL_PATIENTS_URL)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @DisplayName("Should add a patient given the user has a valid identity")
    void addUserGivenIdentityIsValid() {
        Patient patient =
                new Patient(
                        "12345",
                        "Arty",
                        "Muza",
                        DATE_OF_BIRTH,
                        "Flat 7, Elm Rose Road, E16 9AA"
                );
        given(patientService.addPatient(patient)).willReturn(just(patient));

        client
                .put()
                .uri(ALL_PATIENTS_URL)
                .body(just(patient), Patient.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    @DisplayName("Should update existing patient")
    void updateExistingPatient() {
        Patient patient =
                new Patient(
                        "12345",
                        "Arty",
                        "Muza",
                        DATE_OF_BIRTH,
                        "Flat 7, Elm Rose Road, E16 9AA"
                );
        given(patientService.updatePatient(patient)).willReturn(just(patient));

        client
                .post()
                .uri(ALL_PATIENTS_URL)
                .body(just(patient), Patient.class)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @DisplayName("Should get an existing patient")
    void retrievePatient() {
        Patient patient =
                new Patient(
                        "12345",
                        "Arty",
                        "Muza",
                        DATE_OF_BIRTH,
                        "Flat 7, Elm Rose Road, E16 9AA"
                );
        Optional<String> identityRefOptional = Optional.of(patient.getIdentityRef());
        given(patientService.getPatient(identityRefOptional)).willReturn(just(patient));

        client
                .get()
                .uri(format(PATIENT_URL, patient.getIdentityRef()))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("@.name").isEqualTo("Arty")
                .jsonPath("@.surname").isEqualTo("Muza")
                .jsonPath("@.identityRef").isEqualTo("12345")
                .jsonPath("@.address").isEqualTo("Flat 7, Elm Rose Road, E16 9AA");
    }

    @Test
    @DisplayName("Should delete an existing patient")
    void deletePatient() {
        client
                .delete()
                .uri(PATIENT_URL)
                .exchange()
                .expectStatus()
                .isOk();

    }
}
