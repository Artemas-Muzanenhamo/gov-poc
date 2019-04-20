package com.gov.zw.client;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class IdentityClientTest extends CDCIdentityClientBaseTest {

    private static final String IDENTITIES_REFERENCE = "/identities/reference";
    private static final String APPLICATION_JSON_UTF_8_VALUE = "application/json;charset=utf-8";

    @Pact(state = "an identity", provider = "identity-service", consumer = "license-service")
    public RequestResponsePact retrieveIdentityPact(PactDslWithProvider builder) {

        // Set Headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", APPLICATION_JSON_UTF_8_VALUE);

        // What I will send as a Request in the Pact JSON
        Map<String, String> requestObject = new HashMap<>();
        requestObject.put("idRef", "MUZAN1234");
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
                .given("an identity reference number from License Service client")
                .uponReceiving("a request from the License-Service consumer")
                    .path(IDENTITIES_REFERENCE)
                    .method(HttpMethod.POST.name())
                    .body("MUZAN1234", MediaType.TEXT_PLAIN_VALUE)
                .willRespondWith()
                    .status(HttpStatus.OK.value())
                    .headers(headers)
                    .body(responseBodyJson.toJSONString())
                .toPact();
    }

    @Test
    @PactVerification(fragment = "retrieveIdentityPact")
    public void verifyIdentityPact() {
        Map<String, String> map = new HashMap<>();
        map.put("idRef", "MUZAN1234");
        IdentityReferenceJson identityReferenceJson = new IdentityReferenceJson();
        identityReferenceJson.idRef = map.get("idRef");
        Identity identity = identityClient.findIdentityByIdReferenceNumber("MUZAN1234");
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
