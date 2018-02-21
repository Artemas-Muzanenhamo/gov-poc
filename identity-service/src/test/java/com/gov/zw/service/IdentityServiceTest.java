package com.gov.zw.service;

import com.gov.zw.domain.Identity;
import com.gov.zw.util.InvalidIdentityException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class IdentityServiceTest {

    @InjectMocks
    IdentityServiceImpl identityService;

    @Test(expected = InvalidIdentityException.class)
    public void should_throw_an_exception_when_an_invalid_identity_is_passed() throws Exception {
        identityService.save(null);
    }

    @Test
    public void should_save_identity_if_identity_details_exist() throws InvalidIdentityException {
    }
}
