package com.gov.zw.patient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static reactor.core.publisher.Mono.just;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceUnitTest {

    @InjectMocks
    private PatientServiceImpl patientServiceImpl;

    @Mock
    private PatientRepository patientRepository;

    @Test
    public void should_return_all_patients() {
        Patient patient = new Patient("MUZAN123", "Artemas", "Muzanenhamo",
                LocalDate.of(1990, 3, 28),
                "68 Jeremy Street, London, W1 7AA");
        given(patientRepository.findAll()).willReturn(Flux.just(patient));

        Patient actualPatient = patientServiceImpl.getAllPatients().blockFirst();

        assertThat(actualPatient).isEqualTo(patient);
    }

    @Test
    public void should_add_patient_details_given_a_valid_id() {
        Patient patient = new Patient("MUZAN123", "Artemas", "Muzanenhamo",
                LocalDate.of(1990, 3, 28),
                "68 Jeremy Street, London, W1 7AA");
        given(patientRepository.insert(patient)).willReturn(just(patient));

        Mono<Patient> createdPatient = patientServiceImpl.addPatient(patient);

        assertThat(createdPatient.block()).isEqualTo(patient);
    }

    @Test
    public void should_update_patient_details() {
        Patient updatedPatient = new Patient("MUZAN123", "Artemas", "Thomas",
                LocalDate.of(1990, 3, 28),
                "123 Rock Street, London, W1 7XX");

        given(patientRepository.save(updatedPatient)).willReturn(just(updatedPatient));

        Mono<Patient> patient = patientServiceImpl.updatePatient(updatedPatient);

        assertThat(updatedPatient).isEqualTo(patient.block());
    }
}
