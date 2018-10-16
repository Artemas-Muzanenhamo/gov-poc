package com.gov.zw.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class PatientTest {

    private Patient patient;

    @Before
    public void setup() {
        patient = new Patient();
    }

    @Test
    public void shouldReturnEmptyPatientObject() {
        Patient patientDummy = new Patient();
        assertThat(patient).isEqualTo(patientDummy);
    }

    @Test
    public void shouldReturnPatientObjectPopulated() {
        patient = new Patient("Artemas", "Muzanenhamo", LocalDate.of(1990, 3, 28),
                "MUZAN123", "68 Jeremy Street, London, W1 7AA");

        Patient patientDummy =
                new Patient("Artemas", "Muzanenhamo", LocalDate.of(1990, 3, 28),
                        "MUZAN123", "68 Jeremy Street, London, W1 7AA");
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
