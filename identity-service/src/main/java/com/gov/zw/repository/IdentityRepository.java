package com.gov.zw.repository;

import com.gov.zw.domain.Identity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface IdentityRepository extends MongoRepository<Identity, String> {

    List<Identity> findIdentitiesByName(@Param("name") String name);
    List<Identity> findIdentitiesBySurname(@Param("surname") String surname);
    List<Identity> findIdentitiesByVillageOfOrigin(@Param("villageoforigin") String villageOfOrigin);
    List<Identity> findIdentitiesByNameAndSurnameAndVillageOfOrigin(
            @Param("name") String name,
            @Param("surname") String surname,
            @Param("villageoforigin") String villageOfOrigin
    );
    Identity findIdentityByIdentityRef(String identityReference);
}
