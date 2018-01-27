package com.gov.zw.service;

import com.gov.zw.client.Identity;
import com.gov.zw.client.IdentityClient;
import com.gov.zw.domain.License;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

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
        License license = new License(
                "1", "1234AMUZ1", "Muzanenhamo", "Artemas Takudzwa", "28/03/1990", "ZIM", "20/11/2017", "19/11/2027", "ZDVLA",
                "MUZANEN123456ABCDEF", "signature.jpg", "768 Sunningdale 3, Harare, Zimbabwe");
        Mockito.when(licenseServiceImpl.findIdentityByIdReferenceNumber(license))
                .thenReturn(new Identity("1", "1234AMUZ1", "Artemas", "Muzanenhamo", "28/03/1990", "Mashayamombe",
                        "Harare", "22/01/2018"));

        // WHEN
        Identity identity = licenseServiceImpl.findIdentityByIdReferenceNumber(license);

        // THEN
        assertThat(identity.getName()).isEqualTo("Artemas");
        assertThat(identity.getSurname()).isEqualTo("Muzanenhamo");
    }

}
