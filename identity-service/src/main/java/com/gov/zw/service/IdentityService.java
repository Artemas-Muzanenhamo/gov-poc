package com.gov.zw.service;

import com.gov.zw.domain.*;
import com.gov.zw.exception.InvalidIdentityException;
import com.gov.zw.exception.InvalidIdentityNameException;
import com.gov.zw.exception.InvalidIdentityReferenceException;

import java.util.List;

/**
 * This Service allows you to perform all the CRUD
 * operations for an {@link Identity}.
 */
public interface IdentityService {

    /**
     * Allows you to save an {@link Identity}.
     *
     * @param identity - {@link Identity} to be saved.
     * @throws InvalidIdentityException
     */
    void save(Identity identity) throws InvalidIdentityException;

    /**
     * Allows you to find an {@link Identity} by name.
     *
     * @param identityNameJson - Name of the {@link Identity} to be returned.
     * @return - a {@link List<Identity>} of Identities.
     * @throws InvalidIdentityNameException
     */
    List<IdentityJson> findIdentitiesByName(IdentityNameJson identityNameJson) throws InvalidIdentityNameException;

    /**
     * Allows you to find an {@link Identity} by the ID reference.
     *
     * @param identityRefJson - The ID reference of the {@link Identity} to be returned.
     * @return - An {@link Identity}.
     * @throws InvalidIdentityReferenceException
     */
    IdentityJson findIdentityByIdentityRef(IdentityReferenceJson identityRefJson) throws InvalidIdentityReferenceException;

    /**
     * Allows you to retrieve all the Identities.
     *
     * @return - A {@link List<Identity>} of Identities.
     */
    List<IdentityJson> findAll();

    /**
     * Deletes an {@link Identity}.
     *
     * @param identityJson - {@link Identity} to be deleted.
     * @throws InvalidIdentityException
     */
    void delete(IdentityJson identityJson) throws InvalidIdentityException;

}
