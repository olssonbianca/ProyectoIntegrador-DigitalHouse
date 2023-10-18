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
@Table(name = "campus")

public class Campus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer campusId;
    private String campus;
    private String address;
    private String latitude;
    private String length;
    private Boolean isEnabled;
    private Integer provinceId;
    private Integer cityId;

}
