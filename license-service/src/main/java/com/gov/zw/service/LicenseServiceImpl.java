package com.gov.zw.service;

import com.gov.zw.client.Identity;
import com.gov.zw.client.IdentityClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LicenseServiceImpl implements LicenseService {

    private IdentityClient identityClient;

    public LicenseServiceImpl(IdentityClient identityClient){
        this.identityClient = identityClient;
    }

    @Override
    public Identity findIdentityByIdReferenceNumber(Map<String, String> referenceNumber) {
        return identityClient.findIdentityByIdReferenceNumber(referenceNumber);
    }
}
