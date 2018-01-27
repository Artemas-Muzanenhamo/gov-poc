package com.gov.zw.service;

import com.gov.zw.client.Identity;

import java.util.Map;

public interface LicenseService {
    Identity findIdentityByIdReferenceNumber(Map<String, String> referenceNumber);
}
