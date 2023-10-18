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
@Table(name = "experienceCharacteristics")
public class ExperienceCharacteristics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer experienceCharacteristicId;
    private Integer characteristicId;
    private Long experienceId;

    @OneToOne
    @JoinColumn(name = "characteristicId", referencedColumnName = "characteristicId", updatable = false, insertable = false)
    private Characteristics characteristics;


}
