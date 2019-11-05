package com.gov.zw.mapper;

import com.gov.zw.client.IdentityReferenceJson;
import com.gov.zw.client.dto.IdentityReference;

public class IdentityReferenceMapper {
    public static IdentityReference toIdentityReferenceDTO(IdentityReferenceJson identityReferenceJson) {
        return new IdentityReference(identityReferenceJson.getIdRef());
    }
}
