package com.gov.zw.client.identity;

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
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "IdentityService", port = "9999")
@PactFolder("../pacts")
class IdentityClientTest {
    private static final String IDENTITIES_REFERENCE_PATH = "/identities/reference";
    private static final String ID = "1";
    private static final String IDENTITY_REF = "MUZAN1234";
    private static final String NAME = "Artemas";
    private static final String SURNAME = "Muzanenhamo";
    private static final String BIRTH_DATE = "28/03/1990";
    private static final String VILLAGE_OF_ORIGIN = "Mashayamombe";
    private static final String PLACE_OF_BIRTH = "Harare";
    private static final String DATE_OF_ISSUE = "22/01/2018";

    @Pact(state = "an identity for a patient", provider = "identity-service", consumer = "health-service")
    public RequestResponsePact retrievePatientIdentityPact(PactDslWithProvider builder) {

        // Set Headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", APPLICATION_JSON_VALUE);

        // build the request/response
        return builder
                .given("identity details from the Health Service client")
                    .uponReceiving("a request from the Health-Service consumer")
                    .path(IDENTITIES_REFERENCE_PATH)
                    .method(HttpMethod.POST.name())
                    .body(idReferenceJson().toString(), APPLICATION_JSON_VALUE)
                .willRespondWith()
                    .status(HttpStatus.OK.value())
                    .headers(headers)
                    .body(identityJson())
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "retrievePatientIdentityPact")
    void verifyPatientIdentityPact(MockServer mockServer) {
        Identity identity =
                given()
                    .body(idReferenceJson().toString())
                    .accept(APPLICATION_JSON_VALUE)
                    .contentType(APPLICATION_JSON_VALUE)
                .then()
                    .request()
                    .post(mockServer.getUrl() + IDENTITIES_REFERENCE_PATH).as(Identity.class);

        Identity expectedIdentity =
                new Identity(ID, IDENTITY_REF, NAME, SURNAME,
                        BIRTH_DATE, VILLAGE_OF_ORIGIN,
                        PLACE_OF_BIRTH, DATE_OF_ISSUE);
        assertThat(identity.getId()).isEqualTo(expectedIdentity.getId());
        assertThat(identity.getIdentityRef()).isEqualTo(expectedIdentity.getIdentityRef());
        assertThat(identity.getName()).isEqualTo(expectedIdentity.getName());
        assertThat(identity.getSurname()).isEqualTo(expectedIdentity.getSurname());
        assertThat(identity.getBirthDate()).isEqualTo(expectedIdentity.getBirthDate());
        assertThat(identity.getVillageOfOrigin()).isEqualTo(expectedIdentity.getVillageOfOrigin());
        assertThat(identity.getPlaceOfBirth()).isEqualTo(expectedIdentity.getPlaceOfBirth());
        assertThat(identity.getDateOfIssue()).isEqualTo(expectedIdentity.getDateOfIssue());
        assertThat(identity).isEqualTo(expectedIdentity).hasSameHashCodeAs(expectedIdentity);
    }

    // What I will send as a Request in the Pact JSON
    private DslPart idReferenceJson() {
        return new PactDslJsonBody()
                .stringType("idRef", "MUZAN1234")
                .stringType("id-name", "Artemas")
                .stringType("id-surname", "Muzanenhamo");
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
