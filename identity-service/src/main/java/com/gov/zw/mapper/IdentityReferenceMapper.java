package com.gov.zw.mapper;

import com.gov.zw.domain.IdentityReferenceJson;
import com.gov.zw.dto.IdentityReference;

import java.util.Optional;

public class IdentityReferenceMapper {
    private IdentityReferenceMapper() {
        // Hide implicit public constructor
    }

    public static IdentityReference toIdentityRefDTO(IdentityReferenceJson json) {
        return Optional.ofNullable(json)
                .map(IdentityReferenceJson::getIdRef)
                .map(IdentityReference::new)
                .orElse(new IdentityReference());
    }
}
