package com.gov.zw.patient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/patient")
public class PatientController {

    private PatientService patientServiceImpl;

    public PatientController(PatientService patientServiceImpl) {
        this.patientServiceImpl = patientServiceImpl;
    }

    @GetMapping
    public Flux<Patient> getAllPatients() {
        return patientServiceImpl.getAllPatients();
    }
}
