package com.finalprojectc7t3.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rol")


public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idRol")
    private Integer idRol;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "isEnabled")
    private boolean isEnabled;
}
