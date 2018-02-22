package com.gov.zw.service;

import com.gov.zw.domain.Identity;
import com.gov.zw.repository.IdentityRepository;
import com.gov.zw.util.InvalidIdentityException;
import com.gov.zw.util.InvalidIdentityNameException;
import com.gov.zw.util.InvalidIdentityReferenceException;
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
        Optional<String> idReferenceOptional = Optional.ofNullable(idRef);
        if (idReferenceOptional.isPresent()) {
            return identityRepository.findIdentityByIdentityRef(idRef);
        } else {
            throw new InvalidIdentityReferenceException("The ID reference supplied is not valid!");
        }
    }

    @Override
    public List<Identity> findAll() {
        return identityRepository.findAll();
    }

    @Override
    public void delete(Identity identity) throws InvalidIdentityException {
        Optional<Identity> identityOptional = Optional.ofNullable(identity);
        if (identityOptional.isPresent()) {
            identityRepository.delete(identity);
        } else {
            throw new InvalidIdentityException("The Identity to be deleted is invalid!");
        }
    }
}