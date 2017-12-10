package com.gov.zw.controller;

import com.gov.zw.domain.Identity;
import com.gov.zw.repository.IdentityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/identities")
public class IdentityController {

    private final IdentityRepository identityRepository;

    public IdentityController(IdentityRepository identityRepository){
        this.identityRepository = identityRepository;
    }

    // Create
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void saveIdentity(@RequestBody Identity identity) {
        this.identityRepository.save(identity);
    }

    // Retrieve
    @PostMapping(value = "/name", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Identity> getIdentitiesByName(@RequestBody Map<String, String> name){
        return identityRepository.findIdentitiesByName(name.get("name"));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Identity> getIdentities(){
        return identityRepository.findAll();
    }

    //Update
    @PutMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateIdentity(@RequestBody Identity identity){
        this.identityRepository.save(identity);
    }

    // Delete
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteIdentity(@RequestBody Identity identity){
        this.identityRepository.delete(identity);
    }

    @GetMapping(value = "/saveTest")
    public void saveDummyData(){
        Identity identity = new Identity("1","1","Artemas", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017");
        this.identityRepository.save(identity);
    }

}
