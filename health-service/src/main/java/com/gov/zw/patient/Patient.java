package com.gov.zw.patient;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Document
class Patient {

    @Id
    private String identityRef;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String address;
}
