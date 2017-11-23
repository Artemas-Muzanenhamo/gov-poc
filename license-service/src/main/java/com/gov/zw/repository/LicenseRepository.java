package com.gov.zw.repository;

import com.gov.zw.domain.License;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Artemas on 23/11/2017.
 */
public interface LicenseRepository extends MongoRepository<License, String> {
}
