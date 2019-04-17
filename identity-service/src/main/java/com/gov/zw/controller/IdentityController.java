package com.gov.zw.controller;

import com.gov.zw.domain.IdentityJson;
import com.gov.zw.domain.IdentityNameJson;
import com.gov.zw.domain.IdentityRefJson;
import com.gov.zw.service.IdentityService;
import com.gov.zw.util.InvalidIdentityException;
import com.gov.zw.util.InvalidIdentityNameException;
import com.gov.zw.util.InvalidIdentityReferenceException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/identities")
@CrossOrigin(origins = "http://localhost:4200")
public class IdentityController {

    private final IdentityService identityServiceImpl;

    public IdentityController(IdentityService identityServiceImpl) {
        this.identityServiceImpl = identityServiceImpl;
    }

    // Create
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ResponseStatus(value = OK)
    public void saveIdentity(@RequestBody IdentityJson identityJson) throws InvalidIdentityException {
        this.identityServiceImpl.save(identityJson);
    }

    // Retrieve
    @PostMapping(value = "/name", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<IdentityJson> getIdentitiesByName(@RequestBody IdentityNameJson identityNameJson) throws InvalidIdentityNameException {
        return identityServiceImpl.findIdentitiesByName(identityNameJson);
    }

    @PostMapping(value = "/reference", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public IdentityJson getIdentityByReferenceNumber(@RequestBody IdentityRefJson identityRefJson) throws InvalidIdentityReferenceException {
        return identityServiceImpl.findIdentityByIdentityRef(identityRefJson);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<IdentityJson> getIdentities() {
        return identityServiceImpl.findAll();
    }

    //Update
    @PutMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ResponseStatus(OK)
    public void updateIdentity(@RequestBody IdentityJson identityJson) throws InvalidIdentityException {
        this.identityServiceImpl.save(identityJson);
    }

    // Delete
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ResponseStatus(OK)
    public void deleteIdentity(@RequestBody IdentityJson identityJson) throws InvalidIdentityException {
        this.identityServiceImpl.delete(identityJson);
    }

}
