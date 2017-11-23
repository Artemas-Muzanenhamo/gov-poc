package com.gov.zw.controller;

import com.gov.zw.domain.License;
import com.gov.zw.repository.LicenseRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/licenses")
public class LicenseController {

    private final LicenseRepository licenseRepository;

    public LicenseController(LicenseRepository licenseRepository){
        this.licenseRepository = licenseRepository;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<License> getAllLicenses(){
        return this.licenseRepository.findAll();
    }

}
