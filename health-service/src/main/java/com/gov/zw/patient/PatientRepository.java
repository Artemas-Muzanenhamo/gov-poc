package com.gov.zw.patient;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends ReactiveMongoRepository<Patient, String> {
}
