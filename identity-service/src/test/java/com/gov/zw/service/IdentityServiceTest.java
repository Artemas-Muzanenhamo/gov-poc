package com.gov.zw.service;

import com.gov.zw.domain.*;
import com.gov.zw.dto.IdentityName;
import com.gov.zw.dto.IdentityReference;
import com.gov.zw.repository.IdentityRepository;
import com.gov.zw.exception.InvalidIdentityException;
import com.gov.zw.exception.InvalidIdentityNameException;
import com.gov.zw.exception.InvalidIdentityReferenceException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class IdentityServiceTest {

    public static final String ID = "1";
    public static final String IDENTITY_REF = "1";
    public static final String NAME = "Artemas";
    public static final String SURNAME = "Muzanenhamo";
    public static final String BIRTH_DATE = "28/03/1990";
    public static final String VILLAGE_OF_ORIGIN = "Mashayamombe";
    public static final String PLACE_OF_BIRTH = "Harare";
    public static final String DATE_OF_ISSUE = "17/11/2017";
    @InjectMocks
    private IdentityServiceImpl identityService;
    @Mock
    private IdentityRepository identityRepository;
    @Mock
    private IdentityJsonMapper identityJsonMapper;
    @Mock
    private IdentityRefJsonMapper identityRefJsonMapper;
    @Mock
    private IdentityNameJsonMapper identityNameJsonMapper;

    @Test
    void should_throw_an_exception_when_an_invalid_identity_is_passed() {
        assertThrows(InvalidIdentityException.class, () -> identityService.save((Identity) null));
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
        List<Identity> identities = Collections.singletonList(new Identity(ID, IDENTITY_REF, NAME, SURNAME, BIRTH_DATE,
                VILLAGE_OF_ORIGIN, PLACE_OF_BIRTH, DATE_OF_ISSUE));
        IdentityName identityName = new IdentityName("Artemas");
        given(identityRepository.findIdentitiesByName(identityName.getName())).willReturn(identities);

        List<Identity> identitiesByName = identityService.findIdentitiesByName(identityName);

        assertThat(identitiesByName).isNotEmpty();
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
        assertThrows(InvalidIdentityReferenceException.class, () ->identityService.findIdentityByIdentityRef((IdentityReference) null));
    }

    @Test
    void should_return_an_identity_if_id_reference_is_valid() throws InvalidIdentityReferenceException {
        Identity identity = new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017");
        given(identityRepository.findIdentityByIdentityRef(identity.getIdentityRef())).willReturn(identity);

        Identity identityByIdentityRef = identityService.findIdentityByIdentityRef("1");

        assertThat(identityByIdentityRef).isEqualTo(identity);
        assertThat(identityByIdentityRef.getName()).isEqualTo("Artemas");
    }

    @Test
    void should_return_a_list_of_all_identities() {
        List<Identity> identities = Arrays.asList(
                new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                        "Mashayamombe", "Harare", "17/11/2017"),
                new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                        "Mashayamombe", "Harare", "17/11/2017"),
                new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                        "Mashayamombe", "Harare", "17/11/2017")
        );
        given(identityRepository.findAll()).willReturn(identities);
        List<IdentityJson> expectedIdentityListJson = getIdentityListJson(identities);

        List<IdentityJson> identityJsonList = identityService.findAll();

        assertThat(identityJsonList.size()).isEqualTo(3);
        assertThat(identityJsonList).isEqualTo(expectedIdentityListJson);
    }

    @Test
    void should_throw_an_exception_when_an_invalid_identity_is_passed_to_be_deleted() {
        assertThrows(InvalidIdentityException.class, () -> identityService.delete((IdentityJson) null));
    }

    @Test
    void should_delete_an_identity_if_the_identity_is_valid() throws InvalidIdentityException {
        Identity identity = new Identity("1", "1", "Artemas", "Muzanenhamo", "28/03/1990",
                "Mashayamombe", "Harare", "17/11/2017");

        identityService.delete(identity);

        verify(identityRepository, times(1)).delete(identity);
    }
}
