package com.gov.zw.service;

import com.gov.zw.domain.Identity;

import java.util.Map;

public interface LicenseService {

    Identity findIdentityByIdReferenceNumber(Map<String, String> referenceNumber);

}
