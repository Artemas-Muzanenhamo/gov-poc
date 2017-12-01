package com.gov.zw.controller;

import com.gov.zw.domain.Identity;
import com.gov.zw.repository.IdentityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/id")
public class IdentityController {

    private final IdentityRepository identityRepository;

    public IdentityController(IdentityRepository identityRepository){
        this.identityRepository = identityRepository;
    }

    // Create
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void saveIdentity(@RequestBody Identity identity) {
        this.identityRepository.save(identity);
    }

    // Retrieve
    @PostMapping(value = "/name", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Identity> getIdentitiesByName(@RequestBody String name){
        return identityRepository.findIdentitiesByName(name);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Identity> getIdentities(){
        return identityRepository.findAll();
    }

    //Update
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateIdentity(@RequestBody Identity identity){
        this.identityRepository.save(identity);
    }

    // Delete
    @DeleteMapping(value = "/remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteIdentity(@RequestBody Identity identity){
        this.identityRepository.delete(identity);
    }

}
