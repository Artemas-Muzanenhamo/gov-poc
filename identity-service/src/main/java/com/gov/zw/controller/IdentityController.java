package com.gov.zw.controller;

import com.gov.zw.domain.Identity;
import com.gov.zw.domain.IdentityJson;
import com.gov.zw.domain.IdentityNameJson;
import com.gov.zw.domain.IdentityReferenceJson;
import com.gov.zw.dto.IdentityName;
import com.gov.zw.dto.IdentityReference;
import com.gov.zw.exception.InvalidIdentityException;
import com.gov.zw.exception.InvalidIdentityNameException;
import com.gov.zw.exception.InvalidIdentityReferenceException;
import com.gov.zw.service.IdentityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gov.zw.mapper.IdentityListMapper.toIdentitiesJson;
import static com.gov.zw.mapper.IdentityMapper.toIdentityDTO;
import static com.gov.zw.mapper.IdentityMapper.toIdentityJson;
import static com.gov.zw.mapper.IdentityNameMapper.toIdentityNameDTO;
import static com.gov.zw.mapper.IdentityReferenceMapper.toIdentityRefDTO;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/identities")
@CrossOrigin(origins = "http://localhost:4200")
public class IdentityController {

    private final IdentityService identityServiceImpl;

    public IdentityController(IdentityService identityServiceImpl) {
        this.identityServiceImpl = identityServiceImpl;
    }

    @PostMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ResponseStatus(value = OK)
    public void saveIdentity(@RequestBody IdentityJson identityJson) throws InvalidIdentityException {
        Identity identity = toIdentityDTO(identityJson);
        this.identityServiceImpl.save(identity);
    }

    @PostMapping(value = "/name", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<IdentityJson> getIdentitiesByName(@RequestBody IdentityNameJson identityNameJson) throws InvalidIdentityNameException {
        IdentityName identityName = toIdentityNameDTO(identityNameJson);
        List<Identity> identities = identityServiceImpl.findIdentitiesByName(identityName);
        return toIdentitiesJson(identities);
    }

    @PostMapping(value = "/reference", produces = APPLICATION_JSON_UTF8_VALUE)
    public IdentityJson getIdentityByReferenceNumber(@RequestBody IdentityReferenceJson identityRefJson) throws InvalidIdentityReferenceException {
        IdentityReference identityReference = toIdentityRefDTO(identityRefJson);
        Identity identity = identityServiceImpl.findIdentityByIdentityRef(identityReference);
        return toIdentityJson(identity);
    }

    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public List<IdentityJson> getIdentities() {
        List<Identity> identities = identityServiceImpl.findAll();
        return toIdentitiesJson(identities);
    }

    @PutMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ResponseStatus(OK)
    public void updateIdentity(@RequestBody IdentityJson identityJson) throws InvalidIdentityException {
        Identity identity = toIdentityDTO(identityJson);
        this.identityServiceImpl.save(identity);
    }

    @DeleteMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ResponseStatus(OK)
    public void deleteIdentity(@RequestBody IdentityJson identityJson) throws InvalidIdentityException {
        this.identityServiceImpl.delete(identityJson);
    }

}
