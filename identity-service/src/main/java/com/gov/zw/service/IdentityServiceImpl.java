package com.gov.zw.service;

import com.gov.zw.domain.*;
import com.gov.zw.dto.IdentityName;
import com.gov.zw.repository.IdentityRepository;
import com.gov.zw.exception.InvalidIdentityException;
import com.gov.zw.exception.InvalidIdentityNameException;
import com.gov.zw.exception.InvalidIdentityReferenceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class IdentityServiceImpl implements IdentityService {

    private final IdentityRepository identityRepository;
    private final IdentityJsonMapper identityJsonMapper;
    private final IdentityRefJsonMapper identityRefJsonMapper;
    private final IdentityNameJsonMapper identityNameJsonMapper;

    public IdentityServiceImpl(IdentityRepository identityRepository, IdentityJsonMapper identityJsonMapper,
                               IdentityRefJsonMapper identityRefJsonMapper,
                               IdentityNameJsonMapper identityNameJsonMapper) {
        this.identityRepository = identityRepository;
        this.identityJsonMapper = identityJsonMapper;
        this.identityRefJsonMapper = identityRefJsonMapper;
        this.identityNameJsonMapper = identityNameJsonMapper;
    }

    @Override
    public void save(Identity identity) throws InvalidIdentityException {
        Identity validIdentity = Optional.ofNullable(identity)
                .orElseThrow(() -> new InvalidIdentityException("The Identity is invalid!"));
        identityRepository.save(validIdentity);
    }

    @Override
    public IdentityJson findIdentityByIdentityRef(IdentityReferenceJson identityRefJson) throws InvalidIdentityReferenceException {
        String idReference = identityRefJsonMapper.toIdentityRef(identityRefJson);
        Identity identity = findIdentityByIdentityRef(idReference);
        return new IdentityJson(identity);
    }

    @Override
    public List<IdentityJson> findAll() {
        return identityRepository.findAll().stream().map(IdentityJson::new).collect(toList());
    }

    @Override
    public void delete(IdentityJson identityJson) throws InvalidIdentityException {
        Identity identity = identityJsonMapper.toIdentity(identityJson);
        delete(identity);
    }

    @Override
    public List<Identity> findIdentitiesByName(IdentityName identityName) throws InvalidIdentityNameException {
        return findIdentitiesByName(identityName);
    }

    List<IdentityJson> findIdentitiesByName(String name) throws InvalidIdentityNameException {
        List<Identity> identities = Optional.ofNullable(name)
                .map(identityRepository::findIdentitiesByName)
                .orElseThrow(() -> new InvalidIdentityNameException("The name supplied does not exist!"));
        return identities.stream().map(IdentityJson::new).collect(toList());
    }

    Identity findIdentityByIdentityRef(String idRef) throws InvalidIdentityReferenceException {
        return Optional.ofNullable(idRef)
                .map(identityRepository::findIdentityByIdentityRef)
                .orElseThrow(() -> new InvalidIdentityReferenceException("The ID reference supplied is not valid!"));
    }

    void delete(Identity identity) throws InvalidIdentityException {
        Optional.ofNullable(identity)
                .orElseThrow(() -> new InvalidIdentityException("The Identity to be deleted is invalid!"));
        identityRepository.delete(identity);
    }
}
