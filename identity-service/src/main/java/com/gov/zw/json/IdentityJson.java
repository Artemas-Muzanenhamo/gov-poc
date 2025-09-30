package com.gov.zw.json;

public record IdentityJson(String id,
                           String identityRef,
                           String name,
                           String surname,
                           String birthDate,
                           String villageOfOrigin,
                           String placeOfBirth,
                           String dateOfIssue) {
    public static IdentityJson empty() {
        return new IdentityJson(null, null, null, null, null, null, null, null);
    }

}
