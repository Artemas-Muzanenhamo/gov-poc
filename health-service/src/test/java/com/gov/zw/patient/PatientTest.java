package com.gov.zw.patient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class PatientTest {

    private Patient patient;

    @BeforeEach
    void setup() {
        patient = new Patient();
    }

    @Test
    void shouldReturnEmptyPatientObject() {
        Patient patientDummy = new Patient();
        assertThat(patient).isEqualTo(patientDummy);
    }

    @Test
    void shouldReturnPatientObjectPopulated() {
        patient = new Patient("MUZAN123", "Artemas", "Muzanenhamo", LocalDate.of(1990, 3, 28),
                 "68 Jeremy Street, London, W1 7AA");

        Patient patientDummy =
                new Patient("MUZAN123", "Artemas", "Muzanenhamo", LocalDate.of(1990, 3, 28),
                        "68 Jeremy Street, London, W1 7AA");
        assertThat(patient).isEqualTo(patientDummy);
        assertThat(patient.getName()).isEqualTo(patientDummy.getName());
        assertThat(patient.getSurname()).isEqualTo(patientDummy.getSurname());
        assertThat(patient.getDateOfBirth()).isEqualTo(patientDummy.getDateOfBirth());
        assertThat(patient.getIdentityRef()).isEqualTo(patientDummy.getIdentityRef());
        assertThat(patient.getAddress()).isEqualTo(patientDummy.getAddress());
        assertThat(patient.hashCode()).isEqualTo(patientDummy.hashCode());
        assertThat(patient.toString()).isEqualTo(patientDummy.toString());
    }
}
