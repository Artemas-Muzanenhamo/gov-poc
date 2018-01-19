package com.gov.zw.domain;

import java.util.Objects;

public class Patient {

    private String name;
    private String surname;
    private String identityNumber;
    private String address;
    private String patientReport;

    public Patient(String name, String surname, String identityNumber, String address, String patientReport) {
        this.name = name;
        this.surname = surname;
        this.identityNumber = identityNumber;
        this.address = address;
        this.patientReport = patientReport;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getPatientReport() {
        return patientReport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(name, patient.name) &&
                Objects.equals(surname, patient.surname) &&
                Objects.equals(identityNumber, patient.identityNumber) &&
                Objects.equals(address, patient.address) &&
                Objects.equals(patientReport, patient.patientReport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, identityNumber, address, patientReport);
    }
}
