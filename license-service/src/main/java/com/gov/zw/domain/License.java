package com.gov.zw.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Licenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class License {

    @Id
    private String id;
    private String identityRef;
    private String surname;
    private String firstNames;
    private String dateOfBirth;
    private String country;
    private String dateOfIssue;
    private String expiryDate;
    private String agency;
    private String licenseNumber;
    private String signatureImage;
    private String address;
}
