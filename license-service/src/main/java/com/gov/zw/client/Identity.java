package com.gov.zw.client;

public record Identity(
        String id,
        String identityRef,
        String name,
        String surname,
        String birthDate,
        String villageOfOrigin,
        String placeOfBirth,
        String dateOfIssue
) { }
