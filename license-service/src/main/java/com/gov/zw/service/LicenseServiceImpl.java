package com.gov.zw.service;

import com.gov.zw.client.IdentityClient;
import com.gov.zw.domain.Identity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LicenseServiceImpl implements LicenseService {

    @Autowired
    private IdentityClient identityClient;

    @Override
    public Identity findIdentityByIdReferenceNumber(String referenceNumber) {
        return identityClient.findIdentityByIdReferenceNumber(referenceNumber);
    }
}
