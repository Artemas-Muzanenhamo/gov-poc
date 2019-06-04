package com.gov.zw.patient;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Flux<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Mono<Patient> addPatient(Patient patientMono) {
        return patientRepository.insert(patientMono);
    }

    @Override
    public Mono<Patient> updatePatient(Patient updatedPatient) {
        return patientRepository.save(updatedPatient);
    }

    @Override
    public Mono<Patient> getPatient(Optional<String> patientIdOptional) {
        return patientIdOptional.map(patientRepository::findById).orElseGet(Mono::empty);
    }
}
