package com.gov.zw.client.identity;

import au.com.dius.pact.consumer.PactProviderRuleMk2;
import com.gov.zw.client.identity.IdentityClient;
import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CDCIdentityClientBaseTest {

    @Rule
    public PactProviderRuleMk2 stubProvider =
            new PactProviderRuleMk2("identity-service", "localhost", 9999, this);

    @Autowired
    protected IdentityClient identityClient;
}
