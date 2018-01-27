package com.gov.zw.service;

import com.gov.zw.client.Identity;
import com.gov.zw.domain.License;

public interface LicenseService {
    Identity findIdentityByIdReferenceNumber(License license);
}
