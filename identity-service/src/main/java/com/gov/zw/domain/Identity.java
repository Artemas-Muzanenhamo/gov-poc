package com.gov.zw.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Artemas on 18/11/2017.
 */
@Document(collection = "Identities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Identity {

    @Id
    private String id;
    private String identityRef;
    private String name;
    private String surname;
    private String birthDate;
    private String villageOfOrigin;
    private String placeOfBirth;
    private String dateOfIssue;

}
