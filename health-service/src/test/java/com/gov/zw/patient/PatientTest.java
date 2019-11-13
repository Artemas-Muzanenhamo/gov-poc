package com.gov.zw.patient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class PatientTest {

    private static final String IDENTITY_REF = "MUZAN123";
    private static final String NAME = "Artemas";
    private static final String SURNAME = "Muzanenhamo";
    private static final LocalDate DATE_OF_BIRTH = LocalDate.of(1990, 3, 28);
    private static final String ADDRESS = "68 Jeremy Street, London, W1 7AA";
    private Patient patient;

    @BeforeEach
    void setup() {
        patient = new Patient();
    }

    @Test
    @DisplayName("Should return an empty Patient object")
    void returnEmptyPatientObject() {
        Patient patientDummy = new Patient();
        assertThat(patient).isEqualTo(patientDummy);
    }

    @Test
    @DisplayName("Should return a populated Patient object")
    void shouldReturnPatientObjectPopulated() {
        patient = new Patient(IDENTITY_REF, NAME, SURNAME, DATE_OF_BIRTH, ADDRESS);

        Patient patientDummy =
                new Patient(IDENTITY_REF, NAME, SURNAME, DATE_OF_BIRTH, ADDRESS);
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
