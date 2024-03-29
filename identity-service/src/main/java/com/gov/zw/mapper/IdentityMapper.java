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
                identityJson.getId(),
                identityJson.getIdentityRef(),
                identityJson.getName(),
                identityJson.getSurname(),
                identityJson.getBirthDate(),
                identityJson.getVillageOfOrigin(),
                identityJson.getPlaceOfBirth(),
                identityJson.getDateOfIssue()
        );
    }

    public static IdentityJson toIdentityJson(Identity identity) {
        return Optional.ofNullable(identity)
                .map(id -> new IdentityJson(
                        id.getId(),
                        id.getIdentityRef(),
                        id.getName(),
                        id.getSurname(),
                        id.getBirthDate(),
                        id.getVillageOfOrigin(),
                        id.getPlaceOfBirth(),
                        id.getDateOfIssue()))
                .orElse(new IdentityJson());
    }
}
