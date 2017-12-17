package com.gov.zw.service;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.gov.zw.client.Identity;
import com.gov.zw.client.IdentityClient;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.contract.wiremock.WireMockSpring;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 8081)
public class LicenseServiceTest {

    @ClassRule
    public static WireMockClassRule wireMock = new WireMockClassRule(
            WireMockSpring.options().dynamicPort());

    @InjectMocks
    private LicenseServiceImpl licenseService;

    @Mock
    private IdentityClient identityClient;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void givenIdentityClient_shouldFindIdentity() throws Exception {

        wireMock.stubFor(post(urlEqualTo("/identities/reference"))
                .willReturn(aResponse().withHeader("Content-Type", "text/plain").withBody("Hello World!")));

        Map<String, String> referenceNumber = new HashMap<>();
        referenceNumber.put("refNumber", "1");

        //Identity identity = licenseService.findIdentityByIdReferenceNumber(referenceNumber);
//        assertThat(identity.getName()).isEqualTo("Artemas");

        assertThat(identityClient.findIdentityByIdReferenceNumber(referenceNumber)).isEqualTo("Hello World!");
    }

}
