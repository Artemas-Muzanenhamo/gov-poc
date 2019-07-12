package com.gov.zw.client;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactFolder;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "IdentityService", port = "9999")
@PactFolder("../pacts")
public class IdentityClientTest {

    private static final String IDENTITIES_REFERENCE_PATH = "/identities/reference";
    private static final Map<String, String> CONTENT_TYPE_JSON_UTF8 = Collections.singletonMap("Content-Type", "application/json;charset=UTF-8");

    @Pact(state = "an identity", provider = "identity-service", consumer = "license-service")
    public RequestResponsePact retrieveIdentityPact(PactDslWithProvider builder) {
        // Set Headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset:UTF-8");
        // build the request/response
        return builder
                .given("an identity reference number from License Service client")
                    .uponReceiving("a request from the License-Service consumer")
                    .path(IDENTITIES_REFERENCE_PATH)
                    .headers(CONTENT_TYPE_JSON_UTF8)
                    .method(HttpMethod.POST.name())
                    .body(idRefJson().toString())
                .willRespondWith()
                    .status(HttpStatus.OK.value())
                    .headers(CONTENT_TYPE_JSON_UTF8)
                    .body(identityJson().toString())
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "retrieveIdentityPact")
    void verifyIdentityPact(MockServer mockServer) {
        Identity identity = given()
                .body(idRefJson().toString())
                .accept("application/json;charset=UTF-8")
                .contentType("application/json;charset=UTF-8")
                .then()
                .request()
                .post(mockServer.getUrl() + IDENTITIES_REFERENCE_PATH).as(Identity.class);

        Identity expectedIdentity =
                new Identity("1", "MUZAN1234", "Artemas", "Muzanenhamo",
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

    // What I will send as a Request in the Pact JSON
    private DslPart idRefJson() {
        return new PactDslJsonBody()
                .stringType("idRef", "MUZAN1234");
    }

    // What I will get as a Response in the Pact JSON
    private DslPart identityJson() {
        return new PactDslJsonBody()
                .stringType("id", "1")
                .stringType("identityRef", "MUZAN1234")
                .stringType("name", "Artemas")
                .stringType("surname", "Muzanenhamo")
                .stringType("birthDate", "28/03/1990")
                .stringType("villageOfOrigin", "Mashayamombe")
                .stringType("placeOfBirth", "Harare")
                .stringType("dateOfIssue", "22/01/2018");
    }
}
