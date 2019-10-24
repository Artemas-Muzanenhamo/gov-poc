package com.gov.zw.mapper;

import com.gov.zw.domain.IdentityNameJson;
import com.gov.zw.dto.IdentityName;

public class IdentityNameMapper {
    public static IdentityName toIdentityNameDTO(IdentityNameJson json) {
        return new IdentityName(
                json.getName()
        );
    }
}
