package com.gov.zw.controller;

import com.gov.zw.domain.Identity;
import com.gov.zw.service.IdentityService;
import com.gov.zw.util.InvalidIdentityNameException;
import com.gov.zw.util.InvalidIdentityReferenceException;
import com.gov.zw.util.InvalidIdentityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/identities")
public class IdentityController {

    private final IdentityService identityServiceImpl;

    public IdentityController(IdentityService identityServiceImpl) {
        this.identityServiceImpl = identityServiceImpl;
    }

    // Create
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void saveIdentity(@RequestBody Identity identity) throws InvalidIdentityException {
        this.identityServiceImpl.save(identity);
    }

    // Retrieve
    @PostMapping(value = "/name", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Identity> getIdentitiesByName(@RequestBody Map<String, String> name) throws InvalidIdentityNameException {
        return identityServiceImpl.findIdentitiesByName(name.get("name"));
    }

    @PostMapping(value = "/reference", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Identity getIdentityByReferenceNumber(@RequestBody Map<String, String> idReferenceNumber) throws InvalidIdentityReferenceException {
        return identityServiceImpl.findIdentityByIdentityRef(idReferenceNumber.get("idRef"));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Identity> getIdentities() {
        return identityServiceImpl.findAll();
    }

    //Update
    @PutMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateIdentity(@RequestBody Identity identity) throws InvalidIdentityException {
        this.identityServiceImpl.save(identity);
    }

    // Delete
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteIdentity(@RequestBody Identity identity) throws InvalidIdentityException {
        this.identityServiceImpl.delete(identity);
    }

    @GetMapping(value = "/saveTest")
    public void saveDummyData() throws InvalidIdentityException {
        Identity identity = new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017");
        this.identityServiceImpl.save(identity);
    }

}
