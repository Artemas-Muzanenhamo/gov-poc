package com.gov.zw.service;

import com.gov.zw.client.IdentityClient;
import com.gov.zw.domain.Identity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class LicenseServiceTest {

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

        Identity identity = licenseService.findIdentityByIdReferenceNumber("1");

        assertThat(identity.getName()).isEqualTo("Artemas");

    }

}
