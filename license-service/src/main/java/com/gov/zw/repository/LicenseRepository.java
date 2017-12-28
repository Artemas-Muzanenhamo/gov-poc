package com.gov.zw.repository;

import com.gov.zw.domain.License;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LicenseRepository extends MongoRepository<License, String> {
}
