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
@Table(name = "experienceCampus")
public class ExperienceCampus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer experienceCampusId;
    private Integer campusId;
    private Long experienceId;

    @OneToOne
    @JoinColumn(name = "campusId", referencedColumnName = "campusId", updatable = false, insertable = false)
    private Campus campus;

}
