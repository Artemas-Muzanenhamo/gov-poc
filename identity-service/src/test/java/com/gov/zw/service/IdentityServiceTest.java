package com.gov.zw.service;

import com.gov.zw.domain.Identity;
import com.gov.zw.repository.IdentityRepository;
import com.gov.zw.util.InvalidIdentityException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
}
