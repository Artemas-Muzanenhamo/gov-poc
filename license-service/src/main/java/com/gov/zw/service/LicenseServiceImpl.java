package com.gov.zw.service;

import com.gov.zw.client.Identity;
import com.gov.zw.client.IdentityClient;
import com.gov.zw.domain.License;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LicenseServiceImpl implements LicenseService {

    private IdentityClient identityClient;

    public LicenseServiceImpl(IdentityClient identityClient){
        this.identityClient = identityClient;
    }

    @Override
    public Identity findIdentityByIdReferenceNumber(License license) {
        Map<String, String> referenceNumber = new HashMap<>();
        referenceNumber.put("refNumber", license.getIdentityRef());
        return identityClient.findIdentityByIdReferenceNumber(referenceNumber);
    }
}
