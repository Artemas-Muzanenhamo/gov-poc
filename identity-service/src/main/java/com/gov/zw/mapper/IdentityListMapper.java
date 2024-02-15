package com.gov.zw.mapper;

import com.gov.zw.domain.Identity;
import com.gov.zw.json.IdentityJson;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IdentityListMapper {
    private IdentityListMapper() {
        // Hide implicit public constructor
    }

    public static List<IdentityJson> toIdentitiesJson(List<Identity> identities) {
        return Stream.of(identities)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .map(IdentityMapper::toIdentityJson)
                .collect(Collectors.toList());
    }
}
