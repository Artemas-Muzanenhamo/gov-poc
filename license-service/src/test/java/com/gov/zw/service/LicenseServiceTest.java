package com.gov.zw.service;

import com.gov.zw.client.Identity;
import com.gov.zw.client.IdentityClient;
import com.gov.zw.domain.License;
import com.gov.zw.repository.LicenseRepository;
import com.gov.zw.util.IdentityInvalidException;
import com.gov.zw.util.InvalidLicenseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
public class LicenseServiceTest {

    @InjectMocks
    private LicenseServiceImpl licenseService;

    @Mock
    private IdentityClient identityClient;

    @Mock
    private LicenseRepository licenseRepository;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void should_return_an_identity() throws Exception {

        // GIVEN
        Identity expectedIdentity = new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990", "Mashayamombe",
                "Harare", "22/01/2018");
        Map<String, String> idReference = new HashMap<>();
        idReference.put("idRef", "1");

        // WHEN
        when(identityClient.findIdentityByIdReferenceNumber(idReference)).thenReturn(expectedIdentity);

        // THEN RETURN
        License license = new License("1", "1", "Muzanenhamo", "Artemas",
                "28/03/1990", "Zimbabwe", "25 January 2018",
                "25 January 2050", "DVLA", "MUZANATCK1990", "Doc1.png",
                "150 Sunningdale road");

        licenseService.addLicense(license);
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("idRef", "1");
        verify(identityClient, times(1)).findIdentityByIdReferenceNumber(stringMap);
    }

    @Test(expected = IdentityInvalidException.class)
    public void should_return_an_identity_not_valid_exception() throws Exception {
        License license = new License();
        licenseService.addLicense(license);
    }

    @Test
    public void should_return_licenses_from_the_repository(){
        // GIVEN
        License license = new License("1", "1", "Muzanenhamo", "Artemas",
                "28/03/1990", "Zimbabwe", "25 January 2018",
                "25 January 2050", "DVLA", "MUZANATCK1990", "Doc1.png",
                "150 Sunningdale road");
        List<License> licenses = Arrays.asList(license);

        // WHEN
        when(licenseService.getAllLicenses()).thenReturn(licenses);

        // THE RETURN
        assertThat(licenseService.getAllLicenses()).isEqualTo(licenses);
    }

    @Test(expected = InvalidLicenseException.class)
    public void should_throw_an_exception_when_invalid_license_details_are_passed() throws Exception {
        licenseService.updateLicense(null);
    }

    @Test
    public void should_save_when_empty_license_details_are_passed() throws Exception {
        License license = new License();
        licenseService.updateLicense(license);
    }

    @Test(expected = InvalidLicenseException.class)
    public void should_return_an_identity_not_valid_exception_when_trying_to_delete_license() throws Exception {
        licenseService.removeLicense(null);
    }

}
