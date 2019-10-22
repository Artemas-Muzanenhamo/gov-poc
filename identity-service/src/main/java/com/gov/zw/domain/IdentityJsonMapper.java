package com.gov.zw.domain;

import org.springframework.stereotype.Component;

@Component
public class IdentityJsonMapper {
    public Identity toIdentity(IdentityJson json) {
        return new Identity(
                json.getId(),
                json.getIdentityRef(),
                json.getName(),
                json.getSurname(),
                json.getBirthDate(),
                json.getVillageOfOrigin(),
                json.getPlaceOfBirth(),
                json.getDateOfIssue()
        );
    }
}
