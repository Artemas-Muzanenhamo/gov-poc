package com.gov.zw.client.identity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CDCIdentityClientBaseTest {

    @Autowired
    protected IdentityClient identityClient;
}
