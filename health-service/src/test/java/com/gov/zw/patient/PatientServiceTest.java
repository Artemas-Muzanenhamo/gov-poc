package com.gov.zw.patient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class PatientServiceTest {

    @InjectMocks
    private PatientServiceImpl patientServiceImpl;

    @Mock
    private PatientRepository patientRepository;

    @Test
    public void shouldReturnAllPatients() throws Exception {
        Patient patient = new Patient("MUZAN123", "Artemas", "Muzanenhamo", LocalDate.of(1990, 3, 28),
                "68 Jeremy Street, London, W1 7AA");
        given(patientRepository.findAll()).willReturn(Flux.just(patient));

        Patient actualPatient = patientServiceImpl.getAllPatients().blockFirst();

        assertThat(actualPatient).isEqualTo(patient);
    }
}
