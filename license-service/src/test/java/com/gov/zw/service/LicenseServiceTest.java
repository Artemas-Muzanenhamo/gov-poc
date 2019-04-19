package com.gov.zw.service;

import com.gov.zw.client.Identity;
import com.gov.zw.client.IdentityClient;
import com.gov.zw.client.IdentityReferenceJson;
import com.gov.zw.domain.License;
import com.gov.zw.domain.LicenseJson;
import com.gov.zw.domain.LicenseJsonMapper;
import com.gov.zw.repository.LicenseRepository;
import com.gov.zw.util.InvalidIdentityException;
import com.gov.zw.util.InvalidLicenseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
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

    @Mock
    private LicenseJsonMapper licenseJsonMapper;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void should_return_an_identity() throws Exception {

        LicenseJson licenseJson = givenAValidLicense();
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("idRef", "1");
        IdentityReferenceJson identityReferenceJson = new IdentityReferenceJson(stringMap);

        licenseService.addLicense(licenseJson);

        verify(identityClient, times(1)).findIdentityByIdReferenceNumber(identityReferenceJson);
    }

    @Test(expected = InvalidIdentityException.class)
    public void should_return_an_identity_not_valid_exception() throws Exception {
        License license = new License();
        licenseService.addLicense(license);
    }

    @Test
    public void should_return_licenses_from_the_repository() {
        List<License> licenses = getLicenses();
        List<LicenseJson> licenseJsons = givenAValidLicenseJson(licenses);

        List<LicenseJson> allLicenses = licenseService.getAllLicenses();

        assertThat(allLicenses.toString()).isEqualTo(licenseJsons.toString());
        verify(licenseRepository, times(1)).findAll();
    }

    @Test(expected = InvalidLicenseException.class)
    public void should_throw_an_exception_when_invalid_license_json_is_passed() throws Exception {
        licenseService.updateLicense((LicenseJson) null);
    }

    @Test
    public void should_save_when_empty_license_details_are_passed() throws Exception {
        License license = new License();

        licenseService.updateLicense(license);

        verify(this.licenseRepository, times(1)).save(license);
    }

    @Test
    public void should_update_license_details_when_a_valid_license_is_passed() throws Exception {
        License license = givenALicense();

        licenseService.updateLicense(license);

        verify(licenseRepository, times(1)).save(license);
    }

    @Test(expected = InvalidLicenseException.class)
    public void should_return_an_identity_not_valid_exception_when_trying_to_delete_license() throws Exception {
        licenseService.removeLicense((LicenseJson) null);
    }

    @Test
    public void should_delete_a_license_when_a_valid_license_is_passed() throws Exception {
        License license = givenALicense();

        licenseService.removeLicense(license);

        verify(licenseRepository, times(1)).delete(license);
    }

    @Test
    public void should_return_a_license_given_the_identity_reference() throws Exception {
        License license = givenALicense();
        String identityRef = "123";
        when(licenseRepository.findLicenseByIdentityRef(identityRef)).thenReturn(license);

        License licenseByIdentityRef = licenseService.getLicenseByIdentityRef(identityRef);

        assertThat(licenseByIdentityRef).isEqualTo(license);
        verify(licenseRepository, times(1)).findLicenseByIdentityRef(identityRef);
    }

    private Map<String, String> givenIdentityReference() {
        Map<String, String> idReference = new HashMap<>();
        idReference.put("idRef", "1");
        return idReference;
    }

    private Identity expectedIdentity() {
        return new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990", "Mashayamombe",
                "Harare", "22/01/2018");
    }

    private License givenALicense() {
        return new License("1", "1", "Muzanenhamo", "Artemas",
                "28/03/1990", "Zimbabwe", "25 January 2018",
                "25 January 2050", "DVLA", "MUZANATCK1990", "Doc1.png",
                "150 Sunningdale road");
    }

    private List<LicenseJson> givenAValidLicenseJson(List<License> licenses) {
        return licenses.stream().map(LicenseJson::new).collect(Collectors.toList());
    }

    private LicenseJson givenAValidLicense() {
        Identity expectedIdentity = expectedIdentity();
        Map<String, String> idReference = givenIdentityReference();
        License license = givenALicense();
        LicenseJson licenseJson = new LicenseJson(license);
        IdentityReferenceJson identityReferenceJson = new IdentityReferenceJson(idReference);
        given(identityClient.findIdentityByIdReferenceNumber(identityReferenceJson)).willReturn(expectedIdentity);
        given(licenseJsonMapper.toDto(licenseJson)).willReturn(license);
        return licenseJson;
    }

    private List<License> getLicenses() {
        License license = givenALicense();
        List<License> licenses = Collections.singletonList(license);
        when(licenseRepository.findAll()).thenReturn(licenses);
        return licenses;
    }
}
