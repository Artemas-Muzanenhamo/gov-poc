package com.gov.zw.service;

import com.gov.zw.domain.Identity;
import com.gov.zw.domain.IdentityName;
import com.gov.zw.domain.IdentityReference;
import com.gov.zw.exception.InvalidIdentityException;
import com.gov.zw.exception.InvalidIdentityNameException;
import com.gov.zw.exception.InvalidIdentityReferenceException;

import java.util.List;

/**
 * This Service allows you to perform all the CRUD
 * operations for an {@link Identity}.
 */
public interface IdentityService {

    void save(Identity identity) throws InvalidIdentityException;

    List<Identity> findIdentitiesByName(IdentityName identityName) throws InvalidIdentityNameException;

    Identity findIdentityByIdentityRef(IdentityReference identityRef) throws InvalidIdentityReferenceException;

    List<Identity> findAll();

    void delete(Identity identity) throws InvalidIdentityException;

}
