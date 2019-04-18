package com.gov.zw.domain;

import org.springframework.stereotype.Component;

@Component
public class IdentityNameJsonMapper {
    public String toIdentityName(IdentityNameJson json) {
        return json.name;
    }
}
