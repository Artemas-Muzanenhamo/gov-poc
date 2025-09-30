package com.gov.zw.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Identities")
public record Identity(
        @Id
        String id,
        String identityRef,
        String name,
        String surname,
        String birthDate,
        String villageOfOrigin,
        String placeOfBirth,
        String dateOfIssue
) {}
