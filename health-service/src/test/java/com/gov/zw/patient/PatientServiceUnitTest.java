package com.gov.zw.patient;

import com.gov.zw.exceptions.InvalidPatientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @DisplayName("Should return all patients")
    void getAllPatients() {
        Patient patient = new Patient(IDENTITY_REF, NAME, SURNAME, DATE_OF_BIRTH, ADDRESS);
        given(patientRepository.findAll()).willReturn(Flux.just(patient));

        Flux<Patient> patientsFlux = patientServiceImpl.getAllPatients();

        StepVerifier.create(patientsFlux)
                .expectNext(patient)
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Should add patient details given a valid identity reference")
    void addPatientGivenAValidIdentityReference() {
        Patient patient = new Patient(IDENTITY_REF, NAME, SURNAME, DATE_OF_BIRTH, ADDRESS);
        given(patientRepository.insert(patient)).willReturn(just(patient));

        Mono<Patient> patientMono = patientServiceImpl.addPatient(patient);

        StepVerifier.create(patientMono)
                .expectNext(patient)
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Should update patient details")
    void updatePatientDetails() {
        Patient patient = new Patient(IDENTITY_REF, NAME, SURNAME, DATE_OF_BIRTH, ADDRESS);
        when(patientRepository.save(patient)).thenReturn(just(patient));

        Mono<Patient> patientMono = patientServiceImpl.updatePatient(patient);

        StepVerifier.create(patientMono)
                .expectNext(patient)
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Should throw an InvalidPatientException when Patient is null")
    void throwExceptionWhenPatientIsNull() {
        assertThrows(InvalidPatientException.class, () -> patientServiceImpl.updatePatient(null));
    }

    @Test
    @DisplayName("Should retrieve a single patient's details")
    void retrieveSinglePatient() {
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
