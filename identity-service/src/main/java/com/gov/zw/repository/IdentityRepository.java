package com.gov.zw.repository;

import com.gov.zw.domain.Identity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface IdentityRepository extends MongoRepository<Identity, String> {

    List<Identity> findIdentitiesByName(String name);
    List<Identity> findIdentitiesBySurname(String surname);
    List<Identity> findIdentitiesByVillageOfOrigin(String villageOfOrigin);
    List<Identity> findIdentitiesByNameAndSurnameAndVillageOfOrigin(String name, String surname, String villageOfOrigin);

    Identity findIdentityByIdentityRef(String identityReference);
}
