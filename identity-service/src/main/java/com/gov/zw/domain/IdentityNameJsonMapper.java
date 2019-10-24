package com.gov.zw.domain;

import org.springframework.stereotype.Component;

@Component
public class IdentityNameJsonMapper {
    String toIdentityName(IdentityNameJson json) {
        return json.getName();
    }
}
