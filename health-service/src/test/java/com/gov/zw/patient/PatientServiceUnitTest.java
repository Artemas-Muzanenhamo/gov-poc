package com.gov.zw.patient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static reactor.core.publisher.Mono.just;

@ExtendWith(MockitoExtension.class)
class PatientServiceUnitTest {

    private static final String IDENTITY_REF = "MUZAN123";
    private static final String NAME = "Artemas";
    private static final String SURNAME = "Thomas";
    private static final LocalDate DATE_OF_BIRTH = LocalDate.of(1990, 3, 28);
    private static final String ADDRESS = "123 Rock Street, London, W1 7XX";
    private PatientServiceImpl patientServiceImpl;

    @Mock
    private PatientRepository patientRepository;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.initMocks(this);
        patientServiceImpl = new PatientServiceImpl(patientRepository);
    }

    @Test
    void should_return_all_patients() {
        Patient patient = new Patient(IDENTITY_REF, NAME, SURNAME, DATE_OF_BIRTH, ADDRESS);
        given(patientRepository.findAll()).willReturn(Flux.just(patient));

        Flux<Patient> patientsFlux = patientServiceImpl.getAllPatients();

        StepVerifier.create(patientsFlux)
                .expectNext(patient)
                .expectComplete()
                .verify();
    }

    @Test
    void should_add_patient_details_given_a_valid_id() {
        Patient patient = new Patient(IDENTITY_REF, NAME, SURNAME, DATE_OF_BIRTH, ADDRESS);
        given(patientRepository.insert(patient)).willReturn(just(patient));

        Mono<Patient> patientMono = patientServiceImpl.addPatient(patient);

        StepVerifier.create(patientMono)
                .expectNext(patient)
                .expectComplete()
                .verify();
    }

    @Test
    void should_update_patient_details() {
        Patient patient = new Patient(IDENTITY_REF, NAME, SURNAME, DATE_OF_BIRTH, ADDRESS);
        when(patientRepository.save(patient)).thenReturn(just(patient));

        Mono<Patient> patientMono = patientServiceImpl.updatePatient(patient);

        StepVerifier.create(patientMono)
                .expectNext(patient)
                .expectComplete()
                .verify();
    }

    @Test
    void should_retrieve_a_single_patient_details() {
        Patient patient = new Patient(IDENTITY_REF, NAME, SURNAME, DATE_OF_BIRTH, ADDRESS);
        Optional<String> identityRefOptional = Optional.of(patient.getIdentityRef());
        given(patientRepository.findById(patient.getIdentityRef())).willReturn(just(patient));

        Mono<Patient> patientMono = patientServiceImpl.getPatient(identityRefOptional);

        StepVerifier.create(patientMono)
                .expectNext(patient)
                .expectComplete()
                .verify();
    }
}
