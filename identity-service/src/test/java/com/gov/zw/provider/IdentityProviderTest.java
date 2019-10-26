package com.gov.zw.provider;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import com.gov.zw.IdentityServiceApplication;
import com.gov.zw.domain.Identity;
import com.gov.zw.dto.IdentityReference;
import com.gov.zw.service.IdentityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Provider("identity-service")
@PactFolder("../pacts")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = IdentityServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = "server.port=8080")
public class IdentityProviderTest {

    private static final String ID = "1";
    private static final String IDENTITY_REF = "1";
    private static final String NAME = "Artemas";
    private static final String SURNAME = "Muzanenhamo";
    private static final String BIRTH_DATE = "28/03/1990";
    private static final String VILLAGE_OF_ORIGIN = "Mashayamombe";
    private static final String PLACE_OF_BIRTH = "Harare";
    private static final String DATE_OF_ISSUE = "22/01/2018";
    @MockBean
    private IdentityService identityService;

    private static final String IDENTITIES_REFERENCE = "/identities/reference";

    @LocalServerPort private int serverPort;

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @BeforeEach
    void setTarget(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", serverPort));
    }

    @State({
            "an identity reference number from License Service client",
            "identity details from the Health Service client"
    })
    public void identityDetailsState() throws Exception {

        Identity identity = new Identity(ID, IDENTITY_REF, NAME, SURNAME,
                BIRTH_DATE, VILLAGE_OF_ORIGIN, PLACE_OF_BIRTH, DATE_OF_ISSUE);

        when(identityService.findIdentityByIdentityRef(any(IdentityReference.class))).thenReturn(identity);
    }
}
