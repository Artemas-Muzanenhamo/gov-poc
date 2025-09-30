package com.gov.zw.mapper;

import com.gov.zw.domain.Identity;
import com.gov.zw.json.IdentityJson;

import java.util.Optional;

public class IdentityMapper {
    private IdentityMapper() {
        // Hide implicit public constructor
    }

    public static Identity toIdentityDTO(IdentityJson identityJson) {
        return new Identity(
                identityJson.id(),
                identityJson.identityRef(),
                identityJson.name(),
                identityJson.surname(),
                identityJson.birthDate(),
                identityJson.villageOfOrigin(),
                identityJson.placeOfBirth(),
                identityJson.dateOfIssue()
        );
    }

    public static IdentityJson toIdentityJson(Identity identity) {
        return Optional.ofNullable(identity)
                .map(id -> new IdentityJson(
                        id.id(),
                        id.identityRef(),
                        id.name(),
                        id.surname(),
                        id.birthDate(),
                        id.villageOfOrigin(),
                        id.placeOfBirth(),
                        id.dateOfIssue()))
                .orElse(IdentityJson.empty());
    }
}
