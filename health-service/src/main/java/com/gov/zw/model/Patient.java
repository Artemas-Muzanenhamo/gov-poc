package com.gov.zw.model;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
class Patient {

    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String identityRef;
    private String address;
}
