package com.gov.zw.patient;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

record Patient(
        @Id
        String identityRef,
        String name,
        String surname,
        LocalDate dateOfBirth,
        String address
) {
    public static Patient empty() {
        return new Patient(null, null, null, null, null);
    }
}
