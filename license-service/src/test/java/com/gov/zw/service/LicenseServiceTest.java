package com.gov.zw.service;

import org.junit.Before;
import org.mockito.InjectMocks;

import static org.mockito.MockitoAnnotations.initMocks;

public class LicenseServiceTest {

    @InjectMocks
    LicenseServiceImpl licenseService;

    @Before
    public void setUp(){
        initMocks(this);
    }



}
