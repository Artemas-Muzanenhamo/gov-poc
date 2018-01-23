package com.gov.zw.client;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import net.minidev.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IdentityClientTest {

    @Rule
    public PactProviderRuleMk2 stubProvider =
            new PactProviderRuleMk2("identity-service", "localhost", 9999, this);

    @Autowired
    private IdentityClient identityClient;

    @Pact(state = "an identity", provider = "identity-service", consumer = "license-service")
    public RequestResponsePact retrieveIdentityPact(PactDslWithProvider builder) {

        // What I will send as a Request in the Pact JSON
        Map<String, String> requestObject = new HashMap<>();
        requestObject.put("idRef", "MUZAN1234");
        JSONObject requestBodyJson = new JSONObject(requestObject);

        // What I will send as a Response in the Pact JSON
        Map<String, String> responseObject = new HashMap<>();
        responseObject.put("id", "1");
        responseObject.put("identityRef", "1");
        responseObject.put("name", "Artemas");
        responseObject.put("surname", "Muzanenhamo");
        responseObject.put("birthDate", "28/03/1990");
        responseObject.put("villageOfOrigin", "Mashayamombe");
        responseObject.put("placeOfBirth", "Harare");
        responseObject.put("dateOfIssue", "22/01/2018");
        JSONObject responseBodyJson = new JSONObject(responseObject);


        return builder
                .given("an identity reference number")
                .uponReceiving("a request to the identity-service client")
                    .path("/identities/reference")
                    .method("POST")
                    .body(requestBodyJson.toJSONString(), "application/json;charset=UTF-8")
                .willRespondWith()
                    .status(200)
                    .body(responseBodyJson.toJSONString(), "application/json;charset=UTF-8")
                .toPact();
    }

    @Test
    @PactVerification(fragment = "retrieveIdentityPact")
    public void verifyIdentityPact() {
        Map<String, String> map = new HashMap<>();
        map.put("idRef", "MUZAN1234");
        Identity identity = identityClient.findIdentityByIdReferenceNumber(map);
        Identity expectedIdentity = new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990", "Mashayamombe",
                "Harare", "22/01/2018");
        assertThat(identity.getName()).isEqualTo(expectedIdentity.getName());
        assertThat(identity.getSurname()).isEqualTo(expectedIdentity.getSurname());
    }
}
