package com.gov.zw.service;

import com.gov.zw.client.Identity;
import com.gov.zw.client.IdentityClient;
import com.gov.zw.domain.License;
import com.gov.zw.repository.LicenseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
public class LicenseServiceTest {

    @InjectMocks
    private LicenseServiceImpl licenseServiceImpl;

    @Mock
    private IdentityClient identityClient;

    @Mock
    private LicenseRepository licenseRepository;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void should_return_an_identity(){
        License license = new License("1", "1", "Muzanenhamo", "Artemas",
                "28/03/1990", "Zimbabwe", "25 January 2018",
                "25 January 2050", "DVLA", "MUZANATCK1990", "Doc1.png",
                "150 Sunningdale road");
        licenseServiceImpl.addLicense(license);
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("idRef", "1");
        verify(identityClient, times(1)).findIdentityByIdReferenceNumber(stringMap);
    }

}
