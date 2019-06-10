package com.gov.zw.patient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static reactor.core.publisher.Mono.just;

@ExtendWith(MockitoExtension.class)
class PatientServiceUnitTest {

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
        Patient patient = new Patient("MUZAN123", "Artemas", "Muzanenhamo",
                LocalDate.of(1990, 3, 28),
                "68 Jeremy Street, London, W1 7AA");
        given(patientRepository.findAll()).willReturn(Flux.just(patient));

        Patient actualPatient = patientServiceImpl.getAllPatients().blockFirst();

        assertThat(actualPatient).isEqualTo(patient);
    }

    @Test
    void should_add_patient_details_given_a_valid_id() {
        Patient patient = new Patient("MUZAN123", "Artemas", "Muzanenhamo",
                LocalDate.of(1990, 3, 28),
                "68 Jeremy Street, London, W1 7AA");
        given(patientRepository.insert(patient)).willReturn(just(patient));

        Mono<Patient> createdPatient = patientServiceImpl.addPatient(patient);

        assertThat(createdPatient.block()).isEqualTo(patient);
    }

    // TODO: Fix these to use JUNIT 5
    @Test
    void should_update_patient_details() {
        Patient updatedPatient = new Patient("MUZAN123", "Artemas", "Thomas",
                LocalDate.of(1990, 3, 28),
                "123 Rock Street, London, W1 7XX");
        when(patientRepository.save(updatedPatient)).thenReturn(just(updatedPatient));

        Mono<Patient> patient = patientServiceImpl.updatePatient(updatedPatient);

        assertThat(updatedPatient).isEqualTo(patient.block());
    }

    @Test
    void should_retrieve_a_single_patient_details() {
        Patient patient = new Patient("MUZAN123", "Artemas", "Thomas",
                LocalDate.of(1990, 3, 28),
                "123 Rock Street, London, W1 7XX");
        Optional<String> identityRefOptional = Optional.of(patient.getIdentityRef());
        given(patientRepository.findById(patient.getIdentityRef())).willReturn(just(patient));

        Mono<Patient> patientMono = patientServiceImpl.getPatient(identityRefOptional);

        assertThat(patient).isEqualTo(patientMono.block());
    }
}
