package com.gov.zw.service;

import com.gov.zw.client.Identity;
import com.gov.zw.client.IdentityClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class LicenseServiceTest {

    @InjectMocks
    private LicenseServiceImpl licenseServiceImpl;

    @Mock
    private IdentityClient identityClient;

    @Test
    public void should_return_an_identity(){
        // GIVEN
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("refNumber", "MUZAN1234");
        Mockito.when(identityClient.findIdentityByIdReferenceNumber(stringMap))
                .thenReturn(new Identity("1", "MUZAN1234", "Artemas", "Muzanenhamo", "28/03/1990", "Mashayamombe",
                        "Harare", "22/01/2018"));

        // WHEN
        Identity identity = identityClient.findIdentityByIdReferenceNumber(stringMap);

        // THEN
        assertThat(identity.getName()).isEqualTo("Artemas");
        assertThat(identity.getSurname()).isEqualTo("Muzanenhamo");
    }

}
