package com.gov.zw.doctor;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class DoctorTest {

    private Doctor doctor;

    @Before
    public void setup() {
        doctor = new Doctor("Mark", "Smith", Arrays.asList("Surgery"), "DR1234");
    }

    @Test
    public void shouldReturnAllDoctorDetails() {
        Doctor expectedDoctor = new Doctor("Mark", "Smith", Arrays.asList("Surgery"), "DR1234");
        assertThat(doctor).isEqualTo(expectedDoctor);
        assertThat(doctor.getName()).isEqualTo(expectedDoctor.getName());
        assertThat(doctor.getSurname()).isEqualTo(expectedDoctor.getSurname());
    }

}