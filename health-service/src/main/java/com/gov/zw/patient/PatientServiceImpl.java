package com.gov.zw.patient;

import com.gov.zw.exceptions.InvalidPatientException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class PatientServiceImpl {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Flux<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Mono<Patient> addPatient(Patient patientMono) {
        return patientRepository.insert(patientMono);
    }

    public Mono<Patient> updatePatient(Patient updatedPatient) {
        Patient patient = Optional.ofNullable(updatedPatient)
                .orElseThrow(() -> new InvalidPatientException("Invalid Patient"));
        return patientRepository.save(patient);
    }

    public Mono<Patient> getPatient(Optional<String> patientIdOptional) {
        return patientIdOptional
                .map(patientRepository::findById)
                .orElseGet(Mono::empty);
    }

    public Mono<Void> deletePatient(Optional<String> patientIdOptional) {
        return patientIdOptional
                .map(patientRepository::deleteById)
                .orElseGet(Mono::empty);
    }
}
