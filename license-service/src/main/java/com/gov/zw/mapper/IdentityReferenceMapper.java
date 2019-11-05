package com.gov.zw.mapper;

import com.gov.zw.client.IdentityReferenceJson;
import com.gov.zw.client.dto.IdentityReference;
import com.gov.zw.client.exception.IdentityReferenceJsonNotValidException;

import java.util.Optional;

public class IdentityReferenceMapper {
    public static IdentityReference toIdentityReferenceDTO(IdentityReferenceJson identityReferenceJson) {
        return Optional.ofNullable(identityReferenceJson)
                .map(IdentityReferenceJson::getIdRef)
                .map(IdentityReference::new)
                .orElseThrow(() -> new IdentityReferenceJsonNotValidException("Identity Reference is not valid!"));
    }
}
