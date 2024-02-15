package com.gov.zw.mapper;

import com.gov.zw.json.IdentityNameJson;
import com.gov.zw.domain.IdentityName;

public class IdentityNameMapper {
    private IdentityNameMapper() {
        // Hide implicit public constructor
    }

    public static IdentityName toIdentityNameDTO(IdentityNameJson json) {
        return new IdentityName(
                json.getName()
        );
    }
}
