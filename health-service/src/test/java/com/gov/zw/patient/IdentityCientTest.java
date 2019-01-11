package com.gov.zw.patient;

import au.com.dius.pact.consumer.PactProviderRuleMk2;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IdentityCientTest {

    private static final String IDENTITIES_REFERENCE = "/identities/reference";
    private static final String APPLICATION_JSON_UTF_8_VALUE = "application/json;charset=utf-8";

    @Rule
    public PactProviderRuleMk2 stubProvider =
            new PactProviderRuleMk2("identity-service", "localhost", 9999, this);

    @Autowired
    private IdentityClient identityClient;

}
