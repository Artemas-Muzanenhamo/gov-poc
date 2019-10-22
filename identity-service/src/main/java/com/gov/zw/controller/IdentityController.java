package com.gov.zw.controller;

import com.gov.zw.domain.Identity;
import com.gov.zw.domain.IdentityJson;
import com.gov.zw.domain.IdentityNameJson;
import com.gov.zw.domain.IdentityReferenceJson;
import com.gov.zw.service.IdentityService;
import com.gov.zw.exception.InvalidIdentityException;
import com.gov.zw.exception.InvalidIdentityNameException;
import com.gov.zw.exception.InvalidIdentityReferenceException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gov.zw.mapper.IdentityMapper.toIdentityDTO;
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

    // Create
    @PostMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ResponseStatus(value = OK)
    public void saveIdentity(@RequestBody IdentityJson identityJson) throws InvalidIdentityException {
        Identity identity = toIdentityDTO(identityJson);
        this.identityServiceImpl.save(identity);
    }

    // Retrieve
    @PostMapping(value = "/name", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<IdentityJson> getIdentitiesByName(@RequestBody IdentityNameJson identityNameJson) throws InvalidIdentityNameException {
        return identityServiceImpl.findIdentitiesByName(identityNameJson);
    }

    @PostMapping(value = "/reference", produces = APPLICATION_JSON_UTF8_VALUE)
    public IdentityJson getIdentityByReferenceNumber(@RequestBody IdentityReferenceJson identityRefJson) throws InvalidIdentityReferenceException {
        return identityServiceImpl.findIdentityByIdentityRef(identityRefJson);
    }

    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public List<IdentityJson> getIdentities() {
        return identityServiceImpl.findAll();
    }

    //Update
    @PutMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ResponseStatus(OK)
    public void updateIdentity(@RequestBody IdentityJson identityJson) throws InvalidIdentityException {
        Identity identity = toIdentityDTO(identityJson);
        this.identityServiceImpl.save(identity);
    }

    // Delete
    @DeleteMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ResponseStatus(OK)
    public void deleteIdentity(@RequestBody IdentityJson identityJson) throws InvalidIdentityException {
        this.identityServiceImpl.delete(identityJson);
    }

}
