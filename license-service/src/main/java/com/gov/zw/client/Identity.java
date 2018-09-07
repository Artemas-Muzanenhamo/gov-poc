package com.gov.zw.client;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Identity {

    private String id;
    private String identityRef;
    private String name;
    private String surname;
    private String birthDate;
    private String villageOfOrigin;
    private String placeOfBirth;
    private String dateOfIssue;
}
