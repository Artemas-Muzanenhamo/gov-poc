package com.gov.zw.repository;

import com.gov.zw.domain.Identity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IdentityRepository extends MongoRepository<Identity, String> {

    List<Identity> findIdentitiesByName(String name);

}
