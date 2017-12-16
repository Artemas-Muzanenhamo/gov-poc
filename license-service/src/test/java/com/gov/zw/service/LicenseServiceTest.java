package com.gov.zw.service;

import com.gov.zw.client.IdentityClient;
import com.gov.zw.domain.Identity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LicenseServiceTest {

    @InjectMocks
    private LicenseServiceImpl licenseService;

    @MockBean
    private IdentityClient identityClient;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void givenIdentityClient_shouldFindIdentity() throws Exception {

        Map<String, String> referenceNumber = new HashMap<>();
        referenceNumber.put("refNumber", "1");

        Identity identity = licenseService.findIdentityByIdReferenceNumber(referenceNumber);

        assertThat(identity.getName()).isEqualTo("Artemas");

    }

}
