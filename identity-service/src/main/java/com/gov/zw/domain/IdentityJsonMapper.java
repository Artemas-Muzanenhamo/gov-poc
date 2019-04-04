package com.gov.zw.domain;

import org.springframework.stereotype.Component;

@Component
public class IdentityJsonMapper {
    public Identity toIdentity(IdentityJson json) {
        return new Identity(
        json.id,
        json.identityRef,
        json.name,
        json.surname,
        json.birthDate,
        json.villageOfOrigin,
        json.placeOfBirth,
        json.dateOfIssue
        );
    }
}
