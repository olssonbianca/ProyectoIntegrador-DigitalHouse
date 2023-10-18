package com.finalprojectc7t3.backend.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "experience")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer experienceId;
    private String experienceName;
    private String description;
    private Long categoryId;
    private Boolean isEnabled;

    @Transient
    private List<String> characteristicsList;

    @OneToMany
    @JoinColumn(name = "experienceId", referencedColumnName = "experienceId", updatable = false, insertable = false)
    List<ExperienceImage> experienceImageList;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "experienceId", referencedColumnName = "experienceId", updatable = false, insertable = false)
    private List<ExperienceCharacteristics> experienceCharacteristicsList;

    public List<String> getCharacteristicsList() {
        return experienceCharacteristicsList == null || experienceCharacteristicsList.isEmpty() ?
                new ArrayList<>() :
                experienceCharacteristicsList.stream().map(i -> i.getCharacteristics().getCharacteristicName()).collect(Collectors.toList());
    }
}
