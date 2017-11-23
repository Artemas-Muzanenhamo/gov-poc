package com.gov.zw.controller;

import com.gov.zw.domain.Identity;
import com.gov.zw.repository.IdentityRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rest/db")
public class IdentityController {

    private final IdentityRepository identityRepository;

    public IdentityController(IdentityRepository identityRepository){
        this.identityRepository = identityRepository;
    }

    @GetMapping(value = "/id/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Identity> getIdentitiesByName(String name){
        return identityRepository.findIdentitiesByName(name);
    }

    @GetMapping(value = "/id", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Identity> getIdentities(){
        return identityRepository.findAll();
    }

    @GetMapping(value = "/id/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void Save(){
        List<Identity> identities = new ArrayList<>(Arrays.asList(
                new Identity("Artemas", "Muzanenhamo", "28/03/1990",
                        "Mashayamombe", "Harare", "17/11/2017"),
                new Identity("Terrence", "Munhengu", "15/04/1980",
                        "Murehwa", "Mutare", "17/11/2017"),
                new Identity("Tichaona", "Chimuchero", "07/12/1960",
                        "Gutu", "Goromhonzi", "17/11/2017"),
                new Identity("Zindoga", "Ncube", "08/01/1976",
                        "Mhondoro", "Harare", "17/11/2017")
        ));

        identities.forEach(t -> this.identityRepository.save(t));
    }

}
