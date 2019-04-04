package com.gov.zw.service;

import com.gov.zw.domain.Identity;
import com.gov.zw.domain.IdentityJson;
import com.gov.zw.domain.IdentityJsonMapper;
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
    private final IdentityJsonMapper identityJsonMapper;

    public IdentityServiceImpl(IdentityRepository identityRepository, IdentityJsonMapper identityJsonMapper) {
        this.identityRepository = identityRepository;
        this.identityJsonMapper = identityJsonMapper;
    }

    @Override
    public void save(Identity identity) throws InvalidIdentityException {
        Optional<Identity> identityOptional = Optional.ofNullable(identity);
        identityRepository.save(identityOptional
                .orElseThrow( () -> new InvalidIdentityException("The Identity is invalid!")));
    }

    @Override
    public void save(IdentityJson identityJson) throws InvalidIdentityException {
        Identity identity = identityJsonMapper.toIdentity(identityJson);
        save(identity);
    }

    @Override
    public List<Identity> findIdentitiesByName(String name) throws InvalidIdentityNameException {
        Optional<String> nameOptional = Optional.ofNullable(name);
        return identityRepository.findIdentitiesByName(nameOptional
                .orElseThrow( () -> new InvalidIdentityNameException("The name supplied does not exist!")));
    }

    @Override
    public Identity findIdentityByIdentityRef(String idRef) throws InvalidIdentityReferenceException {
        Optional<String> idReferenceOptional = Optional.ofNullable(idRef);
        return identityRepository.findIdentityByIdentityRef(idReferenceOptional
                .orElseThrow( () -> new InvalidIdentityReferenceException("The ID reference supplied is not valid!")));
    }

    @Override
    public List<Identity> findAll() {
        return identityRepository.findAll();
    }

    @Override
    public void delete(Identity identity) throws InvalidIdentityException {
        Optional<Identity> identityOptional = Optional.ofNullable(identity);
        identityRepository.delete(identityOptional
                .orElseThrow(() -> new InvalidIdentityException("The Identity to be deleted is invalid!")));
    }
}
