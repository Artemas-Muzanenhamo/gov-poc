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
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "IdentityService", port = "9999")
@PactFolder("../pacts")
public class IdentityClientTest {

    private static final String IDENTITIES_REFERENCE_PATH = "/identities/reference";
    private static final Map<String, String> CONTENT_TYPE_JSON_UTF8 = Collections.singletonMap("Content-Type", "application/json;charset=UTF-8");
    private static final String ID = "1";
    private static final String IDENTITY_REF = "MUZAN1234";
    private static final String NAME = "Artemas";
    private static final String SURNAME = "Muzanenhamo";
    private static final String BIRTH_DATE = "28/03/1990";
    private static final String VILLAGE_OF_ORIGIN = "Mashayamombe";
    private static final String PLACE_OF_BIRTH = "Harare";
    private static final String DATE_OF_ISSUE = "22/01/2018";

    @Pact(state = "an identity", provider = "identity-service", consumer = "license-service")
    public RequestResponsePact retrieveIdentityPact(PactDslWithProvider builder) {
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
                    .body(identityJson())
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

        Identity expectedIdentity = new Identity(ID, IDENTITY_REF, NAME, SURNAME, BIRTH_DATE, VILLAGE_OF_ORIGIN,
                PLACE_OF_BIRTH, DATE_OF_ISSUE);
        assertThat(identity)
                .isNotNull()
                .isEqualToComparingFieldByField(expectedIdentity);
    }

    // What I will send as a Request in the Pact JSON
    private DslPart idRefJson() {
        return new PactDslJsonBody()
                .stringType("idRef", IDENTITY_REF);
    }

    // What I will get as a Response in the Pact JSON
    private DslPart identityJson() {
        return new PactDslJsonBody()
                .stringType("id", ID)
                .stringType("identityRef", IDENTITY_REF)
                .stringType("name", NAME)
                .stringType("surname", SURNAME)
                .stringType("birthDate", BIRTH_DATE)
                .stringType("villageOfOrigin", VILLAGE_OF_ORIGIN)
                .stringType("placeOfBirth", PLACE_OF_BIRTH)
                .stringType("dateOfIssue", DATE_OF_ISSUE);
    }
}
