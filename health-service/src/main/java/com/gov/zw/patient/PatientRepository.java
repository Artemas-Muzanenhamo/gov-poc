package com.gov.zw.patient;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PatientRepository extends ReactiveMongoRepository<Patient, String> {
}
