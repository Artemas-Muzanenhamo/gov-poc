package com.gov.zw.repository;

import com.gov.zw.domain.License;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LicenseRepository extends MongoRepository<License, String> {
    List<License> findLicensesByFirstNames(@Param("name") String name);
    License findLicenseByIdentityRef(String identityRef);
}
