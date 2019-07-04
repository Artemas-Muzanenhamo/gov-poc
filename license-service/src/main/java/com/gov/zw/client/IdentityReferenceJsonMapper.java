package com.gov.zw.client;

import org.springframework.stereotype.Component;

@Component
public class IdentityReferenceJsonMapper {
    public String toIdentityReference(IdentityReferenceJson json) {
        return json.idRef;
    }
}
