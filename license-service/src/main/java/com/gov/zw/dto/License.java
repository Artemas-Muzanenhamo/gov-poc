package com.gov.zw.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Licenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
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
