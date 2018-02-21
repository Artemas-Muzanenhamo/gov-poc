package com.gov.zw.service;

import com.gov.zw.domain.Identity;
import com.gov.zw.util.InvalidIdentityException;

import java.util.List;

public interface IdentityService {

    void save(Identity identity) throws InvalidIdentityException;

    List<Identity> findIdentitiesByName(String name);

    Identity findIdentityByIdentityRef(String idRef);

    List<Identity> findAll();

    void delete(Identity identity);
}
