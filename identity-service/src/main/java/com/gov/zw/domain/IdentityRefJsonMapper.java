package com.gov.zw.domain;

import org.springframework.stereotype.Component;

@Component
public class IdentityRefJsonMapper {
    public String toIdentityRef(IdentityRefJson json) {
        return json.idRef;
    }
}
