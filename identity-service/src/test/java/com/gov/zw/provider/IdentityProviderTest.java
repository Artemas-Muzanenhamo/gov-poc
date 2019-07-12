package com.gov.zw.provider;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import com.gov.zw.IdentityServiceApplication;
import com.gov.zw.domain.Identity;
import com.gov.zw.domain.IdentityJson;
import com.gov.zw.domain.IdentityReferenceJson;
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

        Identity identity = new Identity("1", "1", "Artemas", "Muzanenhamo",
                "28/03/1990", "Mashayamombe", "Harare", "22/01/2018");
        IdentityJson identityJson = new IdentityJson(identity);

        when(identityService.findIdentityByIdentityRef(any(IdentityReferenceJson.class))).thenReturn(identityJson);
    }
}
