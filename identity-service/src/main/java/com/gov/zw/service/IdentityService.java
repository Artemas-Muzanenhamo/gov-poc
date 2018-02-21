package com.gov.zw.service;

import com.gov.zw.domain.Identity;
import com.gov.zw.util.InvalidIdentityException;
import com.gov.zw.util.InvalidIdentityNameException;
import com.gov.zw.util.InvalidIdentityReferenceException;

import java.util.List;

public interface IdentityService {

    void save(Identity identity) throws InvalidIdentityException;

    List<Identity> findIdentitiesByName(String name) throws InvalidIdentityNameException;

    Identity findIdentityByIdentityRef(String idRef) throws InvalidIdentityReferenceException;

    List<Identity> findAll();

    void delete(Identity identity) throws InvalidIdentityException;
}
