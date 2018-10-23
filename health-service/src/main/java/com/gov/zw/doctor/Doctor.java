package com.gov.zw.doctor;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Doctor {
    private String name;
    private String surname;
    private List<String> practices;
    private String practiceLicenseRef;
}
