package com.gov.zw.patient;

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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IdentityClientTest {

    private static final String IDENTITIES_REFERENCE = "/identities/reference";
    private static final String APPLICATION_JSON_UTF_8_VALUE = "application/json;charset=utf-8";

    @Rule
    public PactProviderRuleMk2 stubProvider =
            new PactProviderRuleMk2("identity-service", "localhost", 9999, this);

    @Autowired
    private IdentityClient identityClient;

    @Pact(state = "an identity for a patient", provider = "identity-service", consumer = "health-service")
    public RequestResponsePact retrievePatientIdentityPact(PactDslWithProvider builder) {

        // Set Headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", APPLICATION_JSON_UTF_8_VALUE);

        // What I will send as a Request in the Pact JSON
        Map<String, String> requestObject = new HashMap<>();
        requestObject.put("idRef", "MUZAN1234");
        requestObject.put("id-name", "Artemas");
        requestObject.put("id-surname", "Muzanenhamo");
        JSONObject requestBodyJson = new JSONObject(requestObject);

        // What I will get as a Response in the Pact JSON
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

        // build the request/response
        return builder
                .given("identity details from the Health Service client")
                .uponReceiving("a request from the Health-Service consumer")
                .path(IDENTITIES_REFERENCE)
                .method(HttpMethod.POST.name())
                .body(requestBodyJson.toJSONString(), MediaType.APPLICATION_JSON_UTF8_VALUE)
                .willRespondWith()
                .status(HttpStatus.OK.value())
                .headers(headers)
                .body(responseBodyJson.toJSONString())
                .toPact();
    }

    @Test
    @PactVerification(fragment = "retrievePatientIdentityPact")
    public void verifyPatientIdentityPact() {
        Map<String, String> map = new HashMap<>();
        map.put("idRef", "MUZAN1234");
        map.put("id-name", "Artemas");
        map.put("id-surname", "Muzanenhamo");
        Identity identity = identityClient.findIdentityByIdReferenceNumber(map);
        Identity expectedIdentity =
                new Identity("1", "1", "Artemas", "Muzanenhamo",
                        "28/03/1990", "Mashayamombe",
                        "Harare", "22/01/2018");
        assertThat(identity.getId()).isEqualTo(expectedIdentity.getId());
        assertThat(identity.getIdentityRef()).isEqualTo(expectedIdentity.getIdentityRef());
        assertThat(identity.getName()).isEqualTo(expectedIdentity.getName());
        assertThat(identity.getSurname()).isEqualTo(expectedIdentity.getSurname());
        assertThat(identity.getBirthDate()).isEqualTo(expectedIdentity.getBirthDate());
        assertThat(identity.getVillageOfOrigin()).isEqualTo(expectedIdentity.getVillageOfOrigin());
        assertThat(identity.getPlaceOfBirth()).isEqualTo(expectedIdentity.getPlaceOfBirth());
        assertThat(identity.getDateOfIssue()).isEqualTo(expectedIdentity.getDateOfIssue());
        assertThat(identity).isEqualTo(expectedIdentity);
        assertThat(identity.hashCode()).isEqualTo(expectedIdentity.hashCode());
    }
}
