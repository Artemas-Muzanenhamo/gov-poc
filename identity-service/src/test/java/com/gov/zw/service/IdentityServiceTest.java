package com.gov.zw.service;

import com.gov.zw.domain.Identity;
import com.gov.zw.domain.IdentityJson;
import com.gov.zw.domain.IdentityJsonMapper;
import com.gov.zw.domain.IdentityRefJson;
import com.gov.zw.repository.IdentityRepository;
import com.gov.zw.util.InvalidIdentityException;
import com.gov.zw.util.InvalidIdentityNameException;
import com.gov.zw.util.InvalidIdentityReferenceException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class IdentityServiceTest {

    @InjectMocks
    private IdentityServiceImpl identityService;

    @Mock
    private IdentityRepository identityRepository;

    @Mock
    private IdentityJsonMapper identityJsonMapper;

    @Test
    void should_throw_an_exception_when_an_invalid_identity_is_passed() {
        assertThrows(InvalidIdentityException.class, () -> identityService.save((IdentityJson) null));
    }

    @Test
    void should_save_identity_if_identity_details_exist() throws InvalidIdentityException {
        Identity identity = new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017");
        identityService.save(identity);
        verify(identityRepository, times(1)).save(identity);
    }

    @Test
    void should_return_a_name_if_name_exists() throws InvalidIdentityNameException {
        List<Identity> identities = Arrays.asList(new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017"));
        List<IdentityJson> expectedIdentityJsonList = getIdentityListJson(identities);
        given(identityRepository.findIdentitiesByName(identities.get(0).getName())).willReturn(identities);

        List<IdentityJson> identityJsonList = identityService.findIdentitiesByName("Artemas");

        assertThat(identityJsonList).isEqualTo(expectedIdentityJsonList);
    }

    @NotNull
    private List<IdentityJson> getIdentityListJson(List<Identity> identities) {
        return identities.stream().map(IdentityJson::new).collect(Collectors.toList());
    }

    @Test
    void should_throw_an_exception_when_an_invalid_name_is_passed() {
        assertThrows(InvalidIdentityNameException.class, () -> identityService.findIdentitiesByName(null));
    }

    @Test
    void should_throw_exception_when__an_invalid_idRef_is_passed() {
        // TODO : Amend this test
//        given(identityJsonMapper.toIdentity(null)).willReturn(null);
        assertThrows(InvalidIdentityReferenceException.class, () ->identityService.findIdentityByIdentityRef((String) null));
    }

    @Test
    void should_return_an_identity_if_id_reference_is_valid() throws InvalidIdentityReferenceException {
        // given
        Identity identity = new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017");
        // when
        when(identityRepository.findIdentityByIdentityRef(identity.getIdentityRef())).thenReturn(identity);
        // then
        assertThat(identityService.findIdentityByIdentityRef("1")).isEqualTo(identity);
        assertThat(identityService.findIdentityByIdentityRef("1").getName()).isEqualTo("Artemas");
    }

    @Test
    void should_return_a_list_of_all_identities() {
        // given
        List<Identity> identities = Arrays.asList(
                new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                        "Mashayamombe", "Harare", "17/11/2017"),
                new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                        "Mashayamombe", "Harare", "17/11/2017"),
                new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                        "Mashayamombe", "Harare", "17/11/2017")
        );
        when(identityRepository.findAll()).thenReturn(identities);
        List<IdentityJson> identityListJson = getIdentityListJson(identities);

        // when
        List<IdentityJson> identityJsonList = identityService.findAll();

        // then return
        assertThat(identityJsonList.size()).isEqualTo(3);
        assertThat(identityJsonList).isEqualTo(identityListJson);
    }

    @Test
    void should_throw_an_exception_when_an_invalid_identity_is_passed_to_be_deleted() {
        assertThrows(InvalidIdentityException.class, () -> identityService.delete((IdentityJson) null));
    }

    @Test
    void should_delete_an_identity_if_the_identity_is_valid() throws InvalidIdentityException {
        // given
        Identity identity = new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017");
        // when
        identityService.delete(identity);
        // then verify
        verify(identityRepository, times(1)).delete(identity);
    }
}