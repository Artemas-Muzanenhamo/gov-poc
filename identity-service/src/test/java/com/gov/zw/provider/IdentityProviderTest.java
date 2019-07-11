package com.gov.zw.provider;

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
import com.gov.zw.domain.Identity;
import net.minidev.json.JSONObject;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.lanwen.wiremock.ext.WiremockResolver;
import ru.lanwen.wiremock.ext.WiremockUriResolver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static com.github.restdriver.clientdriver.ClientDriverRequest.Method.POST;
import static com.github.restdriver.clientdriver.RestClientDriver.giveResponse;
import static com.github.restdriver.clientdriver.RestClientDriver.onRequestTo;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Provider("identity-service")
@PactFolder("../pacts")
@ExtendWith({
        WiremockResolver.class,
        WiremockUriResolver.class
})
public class IdentityProviderTest {

    private static final String IDENTITIES_REFERENCE = "/identities/reference";

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @BeforeEach
    void before(PactVerificationContext context) throws MalformedURLException {
        context.setTarget(HttpTestTarget.fromUrl(new URL("http://localhost:8080")));
//         context.setTarget(new HttpTestTarget("localhost", 8333, "/"));
    }

//    @ClassRule
//    public static final ClientDriverRule embeddedService = new ClientDriverRule(8333);

    @State({
            "an identity reference number from License Service client",
            "identity details from the Health Service client"
    })
    public void identityDetailsState() {

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

//    @TestTarget
//    public final Target target = new HttpTarget(8333);
}
