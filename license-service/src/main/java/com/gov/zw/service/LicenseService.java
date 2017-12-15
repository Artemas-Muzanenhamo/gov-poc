package com.gov.zw.service;

import com.gov.zw.domain.Identity;

public interface LicenseService {

    Identity findIdentityByIdReferenceNumber(String referenceNumber);

}
