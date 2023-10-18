package com.finalprojectc7t3.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PersonDTO {

    private Integer idPerson;

    private String name;

    private String lastName;

    private String mail;


}
