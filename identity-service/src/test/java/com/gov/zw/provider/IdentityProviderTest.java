package com.gov.zw.provider;

import au.com.dius.pact.provider.PactVerifyProvider;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.restdriver.clientdriver.ClientDriverRule;
import com.gov.zw.IdentityServiceApplication;
import com.gov.zw.domain.Identity;
import com.gov.zw.domain.IdentityJson;
import com.gov.zw.domain.IdentityReferenceJson;
import com.gov.zw.service.IdentityService;
import com.gov.zw.util.InvalidIdentityReferenceException;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.lanwen.wiremock.ext.WiremockResolver;
import ru.lanwen.wiremock.ext.WiremockUriResolver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static com.github.restdriver.clientdriver.ClientDriverRequest.Method.POST;
import static com.github.restdriver.clientdriver.RestClientDriver.giveResponse;
import static com.github.restdriver.clientdriver.RestClientDriver.onRequestTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

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
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        Identity identity = new Identity("1", "1", "Artemas", "Muzanenhamo",
//                "28/03/1990", "Mashayamombe", "Harare", "22/01/2018");
//        Map<String, String> id = objectMapper.convertValue(identity, Map.class);
//        JSONObject identityJsonObject = new JSONObject(id);
//
//        embeddedService.addExpectation(
//                onRequestTo(IDENTITIES_REFERENCE)
//                        .withMethod(POST),
//                giveResponse(identityJsonObject.toJSONString(), APPLICATION_JSON_UTF8_VALUE)
//        );
    }
}
