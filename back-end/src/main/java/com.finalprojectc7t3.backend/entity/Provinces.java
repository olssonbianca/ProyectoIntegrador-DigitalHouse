package com.finalprojectc7t3.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "provinces")

public class Provinces {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer provinceId;
    public String province;

}
