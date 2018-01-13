package com.gov.zw.client;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Resources;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        // overriding provider address
        "addresses.ribbon.listOfServers: localhost:8888"
})
public class IdentityClientTest {

    @Rule
    public PactProviderRuleMk2 stubProvider = new PactProviderRuleMk2("identityServiceProvider", "localhost", 8888, this);

    @Autowired
    private IdentityClient identityClient;

    @Pact(state = "an Identity", provider = "customerServiceProvider", consumer = "identityClient")
    public RequestResponsePact createAddressCollectionResourcePact(PactDslWithProvider builder) {
        return builder
                .given("an Identity")
                .uponReceiving("a request to the address collection resource")
                .path("/addresses/")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body("")
                .toPact();
    }

    @Test
    @Ignore
    @PactVerification(fragment = "createAddressCollectionResourcePact")
    public void verifyAddressCollectionPact() {
        // to do
        Map<String, String> map = new HashMap<>();
        map.put("refNumber", "MUZAN1234");
        Identity identity = identityClient.findIdentityByIdReferenceNumber(map);
        assertThat(identity).isEqualTo("Hello");
    }

    @Test
    public void should_return_identity_by_reference() throws Exception {
        // to do
    }
}
