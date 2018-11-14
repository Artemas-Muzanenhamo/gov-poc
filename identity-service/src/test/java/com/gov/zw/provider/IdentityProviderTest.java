package com.gov.zw.provider;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.restdriver.clientdriver.ClientDriverRequest;
import com.github.restdriver.clientdriver.ClientDriverRule;
import com.gov.zw.domain.Identity;
import net.minidev.json.JSONObject;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;

import java.util.Map;

import static com.github.restdriver.clientdriver.RestClientDriver.giveResponse;
import static com.github.restdriver.clientdriver.RestClientDriver.onRequestTo;

@RunWith(PactRunner.class)
@Provider("identity-service")
@PactFolder("../pacts")
public class IdentityProviderTest {

    private static final String IDENTITIES_REFERENCE = "/identities/reference";

    @ClassRule
    public static final ClientDriverRule embeddedService = new ClientDriverRule(8333);

    @State("an identity reference number")
    public void toPostState() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        Identity identity = new Identity("1", "1", "Artemas", "Muzanenhamo",
                "28/03/1990", "Mashayamombe", "Harare", "22/01/2018");
        Map<String, String> id = objectMapper.convertValue(identity, Map.class);
        JSONObject jsonObject = new JSONObject(id);

        embeddedService.addExpectation(
                onRequestTo(IDENTITIES_REFERENCE)
                        .withMethod(ClientDriverRequest.Method.POST),
                giveResponse(jsonObject.toJSONString(), MediaType.APPLICATION_JSON_UTF8_VALUE)
        );
    }

    @TestTarget
    public final Target target = new HttpTarget(8333);
}
