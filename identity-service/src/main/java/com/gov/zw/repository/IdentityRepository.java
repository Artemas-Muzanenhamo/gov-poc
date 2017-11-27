package com.gov.zw.repository;

import com.gov.zw.domain.Identity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface IdentityRepository extends MongoRepository<Identity, String> {

    List<Identity> findIdentitiesByName(String name);

}
