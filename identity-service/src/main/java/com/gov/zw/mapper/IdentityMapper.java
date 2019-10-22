package com.gov.zw.mapper;

import com.gov.zw.domain.Identity;
import com.gov.zw.domain.IdentityJson;

public class IdentityMapper {
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
}
