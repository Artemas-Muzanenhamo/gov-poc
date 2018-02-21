package com.gov.zw.service;

import com.gov.zw.domain.Identity;
import com.gov.zw.repository.IdentityRepository;
import com.gov.zw.util.InvalidIdentityException;
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
        Optional<Identity> identityOptional = Optional.ofNullable(identity);
        if (identityOptional.isPresent()){
            identityRepository.save(identity);
        } else {
            throw new InvalidIdentityException("The Identity is invalid!");
        }
    }

    @Override
    public List<Identity> findIdentitiesByName(String name) {
        return null;
    }

    @Override
    public Identity findIdentityByIdentityRef(String idRef) {
        return null;
    }

    @Override
    public List<Identity> findAll() {
        return null;
    }

    @Override
    public void delete(Identity identity) {

    }
}
