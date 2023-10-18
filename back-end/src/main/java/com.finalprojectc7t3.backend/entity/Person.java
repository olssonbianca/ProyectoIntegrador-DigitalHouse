package com.finalprojectc7t3.backend.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")


public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idPerson")
    protected Integer idPerson;

    @Column(name = "name")
    protected String name;

    @Column(name = "lastName")
    protected String lastName;

    @Column(name = "mail")
    protected String mail;

}
