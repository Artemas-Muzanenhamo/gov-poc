package com.gov.zw.patient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientService patientServiceImpl;

    @Test
    public void shouldReturnAllPatients() throws Exception {
        Patient patient = new Patient("Artemas", "Muzanenhamo", LocalDate.of(1990, 3, 28),
                "MUZAN123", "68 Jeremy Street, London, W1 7AA");
        assertThat(patientServiceImpl.getAllPatients().blockFirst()).isEqualTo(patient);
    }
}
