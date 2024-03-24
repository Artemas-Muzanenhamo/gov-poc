package com.gov.zw.repository;

import com.gov.zw.domain.License;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LicenseRepository extends MongoRepository<License, String> {
    License findLicenseByIdentityRef(String identityRef);
}
