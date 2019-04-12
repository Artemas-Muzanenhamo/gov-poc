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

import static java.util.stream.Collectors.toList;

@Service
public class IdentityServiceImpl implements IdentityService {

    private final IdentityRepository identityRepository;
    private final IdentityJsonMapper identityJsonMapper;

    public IdentityServiceImpl(IdentityRepository identityRepository, IdentityJsonMapper identityJsonMapper) {
        this.identityRepository = identityRepository;
        this.identityJsonMapper = identityJsonMapper;
    }

    void save(Identity identity) throws InvalidIdentityException {
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
    public List<IdentityJson> findIdentitiesByName(String name) throws InvalidIdentityNameException {
        Optional<String> nameOptional = Optional.ofNullable(name);
        List<Identity> identities = identityRepository.findIdentitiesByName(nameOptional
                .orElseThrow(() -> new InvalidIdentityNameException("The name supplied does not exist!")));
        return identities.stream().map(IdentityJson::new).collect(toList());
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

    void delete(Identity identity) throws InvalidIdentityException {
        Optional<Identity> identityOptional = Optional.ofNullable(identity);
        identityRepository.delete(identityOptional
                .orElseThrow(() -> new InvalidIdentityException("The Identity to be deleted is invalid!")));
    }

    @Override
    public void delete(IdentityJson identityJson) throws InvalidIdentityException {
        Identity identity = identityJsonMapper.toIdentity(identityJson);
        delete(identity);
    }
}
