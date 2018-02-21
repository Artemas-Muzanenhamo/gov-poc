package com.gov.zw.service;

import com.gov.zw.domain.Identity;
import com.gov.zw.repository.IdentityRepository;
import com.gov.zw.util.InvalidIdentityException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class IdentityServiceTest {

    @InjectMocks
    private IdentityServiceImpl identityService;

    @Mock
    private IdentityRepository identityRepository;

    @Test(expected = InvalidIdentityException.class)
    public void should_throw_an_exception_when_an_invalid_identity_is_passed() throws Exception {
        identityService.save(null);
    }

    @Test
    public void should_save_identity_if_identity_details_exist() throws InvalidIdentityException {
        Identity identity = new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017");
        identityService.save(identity);
        verify(identityRepository, times(1)).save(identity);
    }

    @Test
    public void should_return_a_name_if_name_exists() throws InvalidIdentityNameException {
        List<Identity> identities = Arrays.asList(new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017"));
        when(identityRepository.findIdentitiesByName(identities.get(0).getName())).thenReturn(identities);
        assertThat(identityService.findIdentitiesByName("Artemas")).isEqualTo(identities);
    }

    @Test(expected = InvalidIdentityNameException.class)
    public void should_throw_an_exception_when_an_invalid_name_is_passed() throws Exception {
        identityService.findIdentitiesByName(null);
    }

    @Test(expected = InvalidIdentityReferenceException.class)
    public void should_throw_exception_when__an_invalid_idRef_is_passed() throws Exception {
        identityService.findIdentityByIdentityRef(null);
    }

    @Test
    public void should_return_an_identity_if_id_reference_is_valid() throws InvalidIdentityReferenceException {
        Identity identity = new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017");
        when(identityRepository.findIdentityByIdentityRef(identity.getIdentityRef())).thenReturn(identity);
        assertThat(identityService.findIdentityByIdentityRef("1")).isEqualTo(identity);
        assertThat(identityService.findIdentityByIdentityRef("1").getName()).isEqualTo("Artemas");
    }

    @Test
    public void should_return_a_list_of_all_identities() {
        // given
        List<Identity> identities = Arrays.asList(
                new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017"),
                new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                        "Mashayamombe", "Harare", "17/11/2017"),
                new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                        "Mashayamombe", "Harare", "17/11/2017")
        );

        // when
        when(identityRepository.findAll()).thenReturn(identities);

        // then return
        assertThat(identityService.findAll().size()).isEqualTo(3);
        assertThat(identityService.findAll()).isEqualTo(identities);
    }
}