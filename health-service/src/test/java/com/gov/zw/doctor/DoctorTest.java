package com.gov.zw.doctor;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class DoctorTest {

    private Doctor doctor;

    @Before
    public void setup() {
        doctor = new Doctor("Mark", "Smith", Collections.singletonList("Surgery"), "DR1234");
    }

    @Test
    public void shouldReturnAllDoctorDetails() {
        Doctor expectedDoctor = new Doctor("Mark", "Smith", Collections.singletonList("Surgery"), "DR1234");

        assertThat(doctor).isEqualTo(expectedDoctor);
        assertThat(doctor.getName()).isEqualTo(expectedDoctor.getName());
        assertThat(doctor.getSurname()).isEqualTo(expectedDoctor.getSurname());
        assertThat(doctor.getPractices()).isEqualTo(expectedDoctor.getPractices());
        assertThat(doctor.getPracticeLicenseRef()).isEqualTo(expectedDoctor.getPracticeLicenseRef());
    }

}