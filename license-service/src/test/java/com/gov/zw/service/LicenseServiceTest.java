package com.gov.zw.service;

import com.gov.zw.client.Identity;
import com.gov.zw.client.IdentityClient;
import com.gov.zw.client.IdentityReferenceJson;
import com.gov.zw.client.IdentityReferenceJsonMapper;
import com.gov.zw.dto.License;
import com.gov.zw.domain.LicenseJson;
import com.gov.zw.mapper.LicenseJsonMapper;
import com.gov.zw.repository.LicenseRepository;
import com.gov.zw.exception.InvalidLicenseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
class LicenseServiceTest {

    private static final String ID_REF = "1";

    @InjectMocks
    private LicenseServiceImpl licenseService;
    @Mock
    private IdentityClient identityClient;
    @Mock
    private LicenseRepository licenseRepository;
    @Mock
    private LicenseJsonMapper licenseJsonMapper;
    @Mock
    private IdentityReferenceJsonMapper identityReferenceJsonMapper;

    @BeforeEach
    void setUp() {
        initMocks(this);
        licenseService = new LicenseServiceImpl(identityClient, licenseRepository, licenseJsonMapper, identityReferenceJsonMapper);
    }

    @Test
    void should_return_an_identity() throws Exception {

        LicenseJson licenseJson = givenAValidLicense();
        IdentityReferenceJson identityReferenceJson = new IdentityReferenceJson(ID_REF);
        given(identityClient.findIdentityByIdReferenceNumber(identityReferenceJson)).willReturn(expectedIdentity());

        licenseService.addLicense(licenseJson);

        verify(identityClient, times(1)).findIdentityByIdReferenceNumber(identityReferenceJson);
    }

    @Test
    @DisplayName("Should throw an InvalidIdentityException when an ID ref that is not an INT is passed")
    void shouldThrowAnInvalidIdentityExceptionFromInvalidStringIdRef() throws Exception {

        License license = new License();
        license.setId("Artemas");
        LicenseJson licenseJson = new LicenseJson(license);

        assertThrows(InvalidLicenseException.class, () -> licenseService.addLicense(licenseJson));
    }

    @Test
    void should_return_an_identity_not_valid_exception() throws Exception {
        License license = new License();
        LicenseJson licenseJson = new LicenseJson(license);
        assertThrows(InvalidLicenseException.class, () -> licenseService.addLicense(licenseJson));
    }

    @Test
    void should_return_licenses_from_the_repository() {
        List<License> licenses = getLicenses();
        List<LicenseJson> licenseJsons = givenAValidLicenseJson(licenses);

        List<LicenseJson> allLicenses = licenseService.getAllLicenses();

        assertThat(allLicenses.toString()).isEqualTo(licenseJsons.toString());
        verify(licenseRepository, times(1)).findAll();
    }

    @Test
    void should_throw_an_exception_when_invalid_license_json_is_passed() {
        assertThrows(InvalidLicenseException.class, () -> licenseService.updateLicense((LicenseJson) null));
    }

    @Test
    void should_save_when_empty_license_details_are_passed() throws Exception {
        License license = new License();

        licenseService.updateLicense(license);

        verify(this.licenseRepository, times(1)).save(license);
    }

    @Test
    void should_update_license_details_when_a_valid_license_is_passed() throws Exception {
        License license = givenALicense();

        licenseService.updateLicense(license);

        verify(licenseRepository, times(1)).save(license);
    }

    @Test
    void should_return_an_identity_not_valid_exception_when_trying_to_delete_license() {
        assertThrows(InvalidLicenseException.class, () -> licenseService.removeLicense((LicenseJson) null));
    }

    @Test
    void should_delete_a_license_when_a_valid_license_is_passed() throws Exception {
        License license = givenALicense();

        licenseService.removeLicense(license);

        verify(licenseRepository, times(1)).delete(license);
    }

    @Test
    void should_return_a_license_given_the_identity_reference() throws Exception {
        License license = givenALicense();
        given(licenseRepository.findLicenseByIdentityRef(ID_REF)).willReturn(license);

        License licenseByIdentityRef = licenseService.getLicenseByIdentityRef(ID_REF);

        assertThat(licenseByIdentityRef).isEqualTo(license);
        verify(licenseRepository, times(1)).findLicenseByIdentityRef(ID_REF);
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
        License license = givenALicense();
        LicenseJson licenseJson = new LicenseJson(license);
        IdentityReferenceJson identityReferenceJson = new IdentityReferenceJson(ID_REF);
        given(identityClient.findIdentityByIdReferenceNumber(identityReferenceJson)).willReturn(expectedIdentity);
        given(licenseJsonMapper.toDto(licenseJson)).willReturn(license);
        return licenseJson;
    }

    private List<License> getLicenses() {
        License license = givenALicense();
        List<License> licenses = Collections.singletonList(license);
        given(licenseRepository.findAll()).willReturn(licenses);
        return licenses;
    }
}
