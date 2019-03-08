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
import static reactor.core.publisher.Mono.justOrEmpty;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceUnitTest {

    @InjectMocks
    private PatientServiceImpl patientServiceImpl;

    @Mock
    private PatientRepository patientRepository;

    @Test
    public void shouldReturnAllPatients() {
        Patient patient = new Patient("MUZAN123", "Artemas", "Muzanenhamo", LocalDate.of(1990, 3, 28),
                "68 Jeremy Street, London, W1 7AA");
        given(patientRepository.findAll()).willReturn(Flux.just(patient));

        Patient actualPatient = patientServiceImpl.getAllPatients().blockFirst();

        assertThat(actualPatient).isEqualTo(patient);
    }

    @Test
    public void should_add_patient_details_given_a_valid_id() {
        Patient patient = new Patient("MUZAN123", "Artemas", "Muzanenhamo", LocalDate.of(1990, 3, 28),
                "68 Jeremy Street, London, W1 7AA");
        Mono<Patient> patientMono =  justOrEmpty(patient);
        given(patientRepository.insert(patient)).willReturn(patientMono);

        Patient patient1 = patientServiceImpl.addPatient(patientMono).block();

        assertThat(patient1).isEqualTo(patient);
    }
}
