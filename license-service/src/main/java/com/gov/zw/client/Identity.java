package com.gov.zw.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
