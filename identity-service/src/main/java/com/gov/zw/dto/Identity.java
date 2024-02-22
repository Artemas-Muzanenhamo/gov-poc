package com.gov.zw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Identities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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
