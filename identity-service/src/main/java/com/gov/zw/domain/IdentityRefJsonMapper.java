package com.gov.zw.domain;

import org.springframework.stereotype.Component;

@Component
public class IdentityRefJsonMapper {
    public String toIdentityRef(IdentityReferenceJson json) {
        return json.idRef;
    }
}
