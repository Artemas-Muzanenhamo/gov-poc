package com.gov.zw.service;

import com.gov.zw.domain.Identity;
import com.gov.zw.domain.IdentityName;
import com.gov.zw.domain.IdentityReference;
import com.gov.zw.exception.InvalidIdentityException;
import com.gov.zw.exception.InvalidIdentityNameException;
import com.gov.zw.exception.InvalidIdentityReferenceException;
import com.gov.zw.repository.IdentityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdentityServiceImpl implements IdentityService {

    private final IdentityRepository identityRepository;

    public IdentityServiceImpl(IdentityRepository identityRepository) {
        this.identityRepository = identityRepository;
    }

    @Override
    public void save(Identity identity) throws InvalidIdentityException {
        Identity validIdentity = Optional.ofNullable(identity)
                .orElseThrow(() -> new InvalidIdentityException("The Identity is invalid!"));
        identityRepository.save(validIdentity);
    }

    @Override
    public Identity findIdentityByIdentityRef(IdentityReference identityRef) throws InvalidIdentityReferenceException {
        return Optional.ofNullable(identityRef)
                .map(IdentityReference::getIdRef)
                .map(identityRepository::findIdentityByIdentityRef)
                .orElseThrow(() -> new InvalidIdentityReferenceException("The ID reference supplied is not valid!"));
    }

    @Override
    public List<Identity> findAll() {
        return identityRepository.findAll();
    }

    @Override
    public void delete(Identity identity) throws InvalidIdentityException {
        Identity id = Optional.ofNullable(identity)
                .orElseThrow(() -> new InvalidIdentityException("The Identity to be deleted is invalid!"));

        identityRepository.delete(id);
    }

    @Override
    public List<Identity> findIdentitiesByName(IdentityName identityName) throws InvalidIdentityNameException {
        return Optional.ofNullable(identityName)
                .map(IdentityName::getName)
                .map(identityRepository::findIdentitiesByName)
                .orElseThrow(() -> new InvalidIdentityNameException("The name supplied does not exist!"));
    }
}
