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
    public List<Identity> findIdentitiesByName(String name) throws InvalidIdentityNameException {
        Optional<String> nameOptional = Optional.ofNullable(name);
        if (nameOptional.isPresent()) {
            return identityRepository.findIdentitiesByName(name);
        } else {
            throw new InvalidIdentityNameException("The name supplied does not exist!");
        }
    }

    @Override
    public Identity findIdentityByIdentityRef(String idRef) throws InvalidIdentityReferenceException {
        Optional<String> idRefenceOptional = Optional.ofNullable(idRef);
        if (idRefenceOptional.isPresent()) {
            return identityRepository.findIdentityByIdentityRef(idRef);
        } else {
            throw new InvalidIdentityReferenceException("The ID reference supplied is not valid!");
        }
    }

    @Override
    public List<Identity> findAll() {
        return null;
    }

    @Override
    public void delete(Identity identity) {

    }
}
