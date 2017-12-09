package com.gov.zw.service;

import org.springframework.stereotype.Service;

@Service
public class LicenseServiceImpl implements LicenseService {

    @Override
    public boolean hasValidIdentity(String identityReference) {
        return false;
    }
}
