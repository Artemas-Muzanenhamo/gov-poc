package com.gov.zw.service;

import com.gov.zw.domain.Identity;
import com.gov.zw.util.InvalidIdentityException;
import com.gov.zw.util.InvalidIdentityNameException;
import com.gov.zw.util.InvalidIdentityReferenceException;

import java.util.List;

/**
 * This Service allows you to perform all the CRUD
 * operations for an {@link Identity}.
 */
public interface IdentityService {

    /**
     * Allows you to save an {@link Identity}.
     * @param identity - {@link Identity} to be saved.
     * @throws InvalidIdentityException
     */
    void save(Identity identity) throws InvalidIdentityException;

    /**
     * Allows you to find an {@link Identity} by name.
     * @param name - Name of the {@link Identity} to be returned.
     * @return - a list of Identities.
     * @throws InvalidIdentityNameException
     */
    List<Identity> findIdentitiesByName(String name) throws InvalidIdentityNameException;

    Identity findIdentityByIdentityRef(String idRef) throws InvalidIdentityReferenceException;

    List<Identity> findAll();

    void delete(Identity identity) throws InvalidIdentityException;
}
