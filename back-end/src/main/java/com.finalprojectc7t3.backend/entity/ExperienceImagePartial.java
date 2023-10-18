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
@Table(name = "experienceimage")
public class ExperienceImagePartial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer experienceImageId;

    @OneToOne
    @JoinColumn(name = "imageId", referencedColumnName = "imageId", insertable = false, updatable = false)
    private Image image;

}
