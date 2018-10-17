package com.gov.zw.patient;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class PatientServiceImpl implements PatientService {

    @Override
    public Flux<Patient> getAllPatients() {
        return null;
    }
}
