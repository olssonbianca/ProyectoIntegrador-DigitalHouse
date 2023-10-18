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
@Table(name = "characteristics")

public class Characteristics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer characteristicId;
    private String characteristicName;

}
