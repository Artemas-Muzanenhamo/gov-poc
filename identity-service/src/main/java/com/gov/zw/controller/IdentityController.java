package com.gov.zw.controller;

import com.gov.zw.domain.Identity;
import com.gov.zw.repository.IdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/db")
public class IdentityController {

    private final IdentityRepository identityRepository;

    public IdentityController(IdentityRepository identityRepository){
        this.identityRepository = identityRepository;
    }

    @GetMapping(value = "/getId/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Identity> getIdentitiesByName(String name){
        return identityRepository.findIdentitiesByName(name);
    }

}
