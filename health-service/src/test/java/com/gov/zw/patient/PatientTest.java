package com.gov.zw.patient;

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

    @Test
    @DisplayName("Should return an empty Patient object")
    void returnEmptyPatientObject() {
        patient = Patient.empty();

        Patient patientDummy = Patient.empty();
        assertThat(patient).isEqualTo(patientDummy);
    }

    @Test
    @DisplayName("Should return a populated Patient object")
    void shouldReturnPatientObjectPopulated() {
        patient = new Patient(IDENTITY_REF, NAME, SURNAME, DATE_OF_BIRTH, ADDRESS);

        Patient patientDummy =
                new Patient(IDENTITY_REF, NAME, SURNAME, DATE_OF_BIRTH, ADDRESS);
        assertThat(patient).isEqualTo(patientDummy);
        assertThat(patient.name()).isEqualTo(patientDummy.name());
        assertThat(patient.surname()).isEqualTo(patientDummy.surname());
        assertThat(patient.dateOfBirth()).isEqualTo(patientDummy.dateOfBirth());
        assertThat(patient.identityRef()).isEqualTo(patientDummy.identityRef());
        assertThat(patient.address()).isEqualTo(patientDummy.address());
        assertThat(patient.hashCode()).hasSameHashCodeAs(patientDummy.hashCode());
        assertThat(patient.toString()).hasToString(patientDummy.toString());
    }
}
